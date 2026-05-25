package com.example.dronetelemetryviewer.feature.connection


sealed class ConnectionEvent {

    data class HostChanged(
        val host: String
    ) : ConnectionEvent()

    data class PortChanged(
        val port: String
    ) : ConnectionEvent()

    data class ProtocolChanged(
        val protocol: String
    ) : ConnectionEvent()

    data object ConnectClicked : ConnectionEvent()
}