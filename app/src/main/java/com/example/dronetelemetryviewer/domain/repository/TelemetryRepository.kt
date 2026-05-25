package com.example.dronetelemetryviewer.domain.repository



import com.example.dronetelemetryviewer.domain.model.TelemetryData
import kotlinx.coroutines.flow.Flow

interface TelemetryRepository {

    suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean

    suspend fun disconnect()

    fun observeTelemetry():
            Flow<TelemetryData>

    fun observeConnection():
            Flow<Boolean>

    suspend fun arm()

    suspend fun disarm()

    suspend fun takeoff()

    suspend fun returnToLaunch()
}