package com.example.dronetelemetryviewer.core.mavlink


import com.example.dronetelemetryviewer.domain.model.TelemetryData
import io.dronefleet.mavlink.common.BatteryStatus
import io.dronefleet.mavlink.common.GlobalPositionInt
import io.dronefleet.mavlink.minimal.Heartbeat

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MavLinkTelemetryReceiver @Inject constructor(
    private val connectionManager: MavLinkConnectionManager,
    private val parser: MavLinkParser
) {

    fun telemetryStream(): Flow<TelemetryData> = flow {

        var telemetryData = TelemetryData()

        while (true) {

            try {

                val connection =
                    connectionManager.getConnection()

                if (connection == null) {

                    telemetryData =
                        telemetryData.copy(
                            connected = false
                        )

                    emit(telemetryData)

                    delay(2000)

                    continue
                }

                val message = connection.next()

                when (val payload = message.payload) {

                    is Heartbeat -> {

                        telemetryData =
                            parser.parseHeartbeat(
                                payload,
                                telemetryData
                            )
                    }

                    is GlobalPositionInt -> {

                        telemetryData =
                            parser.parsePosition(
                                payload,
                                telemetryData
                            )
                    }

                    is BatteryStatus -> {

                        telemetryData =
                            parser.parseBattery(
                                payload,
                                telemetryData
                            )
                    }
                }

                emit(telemetryData)

            } catch (e: Exception) {

                telemetryData =
                    telemetryData.copy(
                        connected = false
                    )

                emit(telemetryData)

                delay(3000)
            }
        }
    }.flowOn(Dispatchers.IO)
}