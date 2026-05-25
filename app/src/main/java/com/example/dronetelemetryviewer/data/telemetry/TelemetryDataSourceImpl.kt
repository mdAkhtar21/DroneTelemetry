package com.example.dronetelemetryviewer.data.telemetry


import com.example.dronetelemetryviewer.core.mavlink.MavLinkTelemetryReceiver
import com.example.dronetelemetryviewer.domain.model.TelemetryData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TelemetryDataSourceImpl @Inject constructor(
    private val telemetryReceiver: MavLinkTelemetryReceiver
) : TelemetryDataSource {

    override fun observeTelemetry(): Flow<TelemetryData> {

        return telemetryReceiver.telemetryStream()
    }
}