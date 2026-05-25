package com.example.dronetelemetryviewer.data.connection



interface ConnectionDataSource {

    suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean

    suspend fun disconnect()
}