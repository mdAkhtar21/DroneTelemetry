package com.example.dronetelemetryviewer.data.telemetry


import com.example.dronetelemetryviewer.domain.model.TelemetryData
import io.dronefleet.mavlink.common.BatteryStatus
import io.dronefleet.mavlink.common.GlobalPositionInt
import io.dronefleet.mavlink.minimal.Heartbeat

import javax.inject.Inject

class TelemetryMapper @Inject constructor() {

    fun mapHeartbeat(
        heartbeat: Heartbeat,
        current: TelemetryData
    ): TelemetryData {

        return current.copy(

            armed = heartbeat.baseMode().value() > 0,

            flightMode = heartbeat.customMode().toString(),

            connected = true
        )
    }

    fun mapPosition(
        position: GlobalPositionInt,
        current: TelemetryData
    ): TelemetryData {

        return current.copy(

            latitude = position.lat() / 1E7,

            longitude = position.lon() / 1E7,

            altitude = position.relativeAlt() / 1000.0
        )
    }

    fun mapBattery(
        battery: BatteryStatus,
        current: TelemetryData
    ): TelemetryData {

        return current.copy(

            battery = battery.batteryRemaining()
        )
    }
}