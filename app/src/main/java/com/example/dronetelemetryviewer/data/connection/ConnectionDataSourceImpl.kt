package com.example.dronetelemetryviewer.data.connection


import com.example.dronetelemetryviewer.core.common.Constants
import com.example.dronetelemetryviewer.core.mavlink.MavLinkConnectionManager
import javax.inject.Inject

class ConnectionDataSourceImpl @Inject constructor(
    private val connectionManager: MavLinkConnectionManager
) : ConnectionDataSource {

    override suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        return when (protocol.uppercase()) {

            Constants.PROTOCOL_UDP -> {
                connectionManager.connectUdp(port)
            }

            Constants.PROTOCOL_TCP -> {
                connectionManager.connectTcp(host, port)
            }

            else -> false
        }
    }

    override suspend fun disconnect() {

        connectionManager.disconnect()
    }
}