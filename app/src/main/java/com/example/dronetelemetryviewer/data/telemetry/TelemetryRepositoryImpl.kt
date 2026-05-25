package com.example.dronetelemetryviewer.data.telemetry

import com.example.dronetelemetryviewer.domain.model.TelemetryData
import com.example.dronetelemetryviewer.domain.repository.TelemetryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelemetryRepositoryImpl @Inject constructor() :
    TelemetryRepository {


    private val telemetryState =
        MutableStateFlow(
            TelemetryData(
                latitude = 28.6139,
                longitude = 77.2090,
                altitude = 10.0,
                battery = 100,
                flightMode = "STABILIZE",
                armed = false,
                connected = true
            )
        )
    val connected = telemetryState.value.connected

    init {

        startTelemetrySimulation()
    }

    private fun startTelemetrySimulation() {

        CoroutineScope(Dispatchers.IO).launch {

            while (connected) {

                try {
                    delay(1000)

                    val current =
                        telemetryState.value

                    telemetryState.value =
                        current.copy(

                            latitude =
                                current.latitude +
                                        Random.nextDouble(
                                            -0.0005,
                                            0.0005
                                        ),

                            longitude =
                                current.longitude +
                                        Random.nextDouble(
                                            -0.0005,
                                            0.0005
                                        ),

                            altitude =
                                Random.nextDouble(
                                    10.0,
                                    100.0
                                ),

                            battery =
                                (current.battery - 1)
                                    .coerceAtLeast(15),

                            flightMode =
                                listOf(
                                    "GUIDED",
                                    "AUTO",
                                    "LOITER",
                                    "RTL"
                                ).random(),

                            connected = true
                        )
                }catch (e: Exception){
                    println("Unsupported packet")
                }
            }
        }
    }

    override suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        return true
    }

    override suspend fun disconnect() {

        telemetryState.value =
            telemetryState.value.copy(
                connected = false
            )
    }

    override fun observeTelemetry():
            Flow<TelemetryData> {

        return telemetryState.asStateFlow()
    }

    override fun observeConnection():
            Flow<Boolean> {

        return MutableStateFlow(true)
    }

    override suspend fun arm() {

        telemetryState.value =
            telemetryState.value.copy(
                armed = true
            )
    }

    override suspend fun disarm() {

        telemetryState.value =
            telemetryState.value.copy(
                armed = false
            )
    }

    override suspend fun takeoff() {

        telemetryState.value =
            telemetryState.value.copy(
                altitude = 50.0,
                flightMode = "TAKEOFF"
            )
    }

    override suspend fun returnToLaunch() {

        telemetryState.value =
            telemetryState.value.copy(
                flightMode = "RTL"
            )
    }
}