package com.example.dronetelemetryviewer.feature.connection

data class ConnectionUiState(

    val host: String = "",

    val port: String = "14550",

    val protocol: String = "UDP",

    val isLoading: Boolean = false,

    val isConnected: Boolean = false,

    val error: String? = null
)