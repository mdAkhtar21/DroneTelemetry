package com.example.dronetelemetryviewer.domain.repository


import com.example.dronetelemetryviewer.core.common.ConnectionState
import kotlinx.coroutines.flow.Flow

interface ConnectionRepository {

    suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean

    suspend fun disconnect()

    suspend fun reconnect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean

    fun observeConnectionState():
            Flow<ConnectionState>
}