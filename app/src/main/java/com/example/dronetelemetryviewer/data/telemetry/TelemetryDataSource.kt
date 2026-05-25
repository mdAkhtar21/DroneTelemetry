package com.example.dronetelemetryviewer.data.telemetry


import com.example.dronetelemetryviewer.domain.model.TelemetryData
import kotlinx.coroutines.flow.Flow

interface TelemetryDataSource {

    fun observeTelemetry(): Flow<TelemetryData>
}