package com.example.dronetelemetryviewer.core.mavlink


import com.example.dronetelemetryviewer.domain.model.TelemetryData
import io.dronefleet.mavlink.common.BatteryStatus
import io.dronefleet.mavlink.common.GlobalPositionInt

import io.dronefleet.mavlink.minimal.Heartbeat
import javax.inject.Inject

class MavLinkParser @Inject constructor() {

    fun parseHeartbeat(
        heartbeat: Heartbeat,
        currentData: TelemetryData
    ): TelemetryData {

        return currentData.copy(

            armed = heartbeat.baseMode().value() > 0,

            flightMode = heartbeat.customMode().toString(),

            connected = true
        )
    }

    fun parsePosition(
        position: GlobalPositionInt,
        currentData: TelemetryData
    ): TelemetryData {

        return currentData.copy(

            latitude = position.lat() / 1E7,

            longitude = position.lon() / 1E7,

            altitude = position.relativeAlt() / 1000.0
        )
    }

    fun parseBattery(
        batteryStatus: BatteryStatus,
        currentData: TelemetryData
    ): TelemetryData {

        val batteryRemaining =
            batteryStatus.batteryRemaining()

        return currentData.copy(

            battery = batteryRemaining
        )
    }
}