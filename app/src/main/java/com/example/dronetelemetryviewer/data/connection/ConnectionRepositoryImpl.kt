package com.example.dronetelemetryviewer.data.connection

import com.example.dronetelemetryviewer.core.common.ConnectionState
import com.example.dronetelemetryviewer.core.common.Constants
import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import java.net.InetAddress


@Singleton
class ConnectionRepositoryImpl @Inject constructor() :
    ConnectionRepository {

    private val connectionState =
        MutableStateFlow(
            ConnectionState.DISCONNECTED
        )

    private var lastHost = ""
    private var lastPort = 0
    private var lastProtocol = ""

    override suspend fun connect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        lastHost = host
        lastPort = port
        lastProtocol = protocol

        connectionState.value =
            ConnectionState.CONNECTING

        delay(1500)

        // Invalid IP
        if (!isValidIp(host)) {

            connectionState.value =
                ConnectionState.ERROR

            return false
        }

        // Invalid Port
        if (port !in 1..65535) {

            connectionState.value =
                ConnectionState.ERROR

            return false
        }

        // Simulated timeout
        if (host == "0.0.0.0") {

            delay(5000)

            connectionState.value =
                ConnectionState.TIMEOUT

            return false
        }

        connectionState.value =
            ConnectionState.CONNECTED

        return true
    }

    override suspend fun disconnect() {

        connectionState.value =
            ConnectionState.DISCONNECTED
    }

    override fun observeConnectionState():
            Flow<ConnectionState> {

        return connectionState.asStateFlow()
    }

    override suspend fun reconnect(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        connectionState.value =
            ConnectionState.RECONNECTING

        repeat(Constants.MAX_RETRY_COUNT) {

            delay(Constants.RECONNECT_DELAY)

            val connected =
                connect(
                    host,
                    port,
                    protocol
                )

            if (connected) {
                return true
            }
        }

        connectionState.value =
            ConnectionState.DISCONNECTED

        return false
    }

    private fun isValidIp(ip: String): Boolean {

        return try {

            InetAddress.getByName(ip)

            true

        } catch (e: Exception) {

            false
        }
    }
}