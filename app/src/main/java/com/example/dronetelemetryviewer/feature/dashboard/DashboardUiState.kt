package com.example.dronetelemetryviewer.feature.dashboard

import com.example.dronetelemetryviewer.core.common.ConnectionState
import com.example.dronetelemetryviewer.domain.model.TelemetryData


data class DashboardUiState(

    val isLoading: Boolean = false,

    val telemetry: TelemetryData =
        TelemetryData(),

    val connectionState:
    ConnectionState =
        ConnectionState.DISCONNECTED,

    val error: String? = null
)
