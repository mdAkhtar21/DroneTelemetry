package com.example.dronetelemetryviewer.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dronetelemetryviewer.core.common.ConnectionState
import com.example.dronetelemetryviewer.core.ui.ErrorView
import com.example.dronetelemetryviewer.core.ui.LoadingView
import com.example.dronetelemetryviewer.core.ui.StatusIndicator

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    // LOADING

    if (state.isLoading) {

        LoadingView()

        return
    }

    val telemetry = state.telemetry

    val connectionState =
        state.connectionState

    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 40.dp,
                start = 10.dp,
                end = 10.dp
            ),

        topBar = {

            Text(
                text = "Telemetry Dashboard",
                style =
                    MaterialTheme.typography
                        .headlineSmall
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            // CONNECTION STATUS

            when (connectionState) {

                ConnectionState.CONNECTED -> {

                    Text(
                        text = "Connected",
                        color = Color.Green
                    )
                }

                ConnectionState.CONNECTING -> {

                    Text(
                        text = "Connecting...",
                        color = Color.Cyan
                    )
                }

                ConnectionState.RECONNECTING -> {

                    Text(
                        text = "Reconnecting...",
                        color = Color.Yellow
                    )
                }

                ConnectionState.TIMEOUT -> {

                    ErrorView(
                        message = "Connection Timeout",
                        onRetry = {
                            viewModel.reconnect()
                        }
                    )
                }

                ConnectionState.ERROR -> {

                    ErrorView(
                        message = "Invalid IP or Port",
                        onRetry = {
                            viewModel.reconnect()
                        }
                    )
                }

                ConnectionState.DISCONNECTED -> {

                    Text(
                        text = "Disconnected",
                        color = Color.Red
                    )
                }
            }

            // STATUS INDICATOR

            StatusIndicator(

                isConnected =
                    telemetry.connected,

                batteryLevel =
                    telemetry.battery
            )

            // TELEMETRY LIST

            LazyColumn(

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                item {

                    TelemetryCard(
                        title = "Latitude",
                        value =
                            telemetry.latitude
                                .toString()
                    )
                }

                item {

                    TelemetryCard(
                        title = "Longitude",
                        value =
                            telemetry.longitude
                                .toString()
                    )
                }

                item {

                    TelemetryCard(
                        title = "Altitude",
                        value =
                            "${telemetry.altitude} m"
                    )
                }

                item {

                    TelemetryCard(
                        title = "Battery",
                        value =
                            "${telemetry.battery}%"
                    )
                }

                item {

                    TelemetryCard(
                        title = "Flight Mode",
                        value =
                            telemetry.flightMode
                                .ifEmpty {
                                    "UNKNOWN"
                                }
                    )
                }

                item {

                    TelemetryCard(
                        title = "Armed",
                        value =
                            if (telemetry.armed)
                                "Yes"
                            else
                                "No"
                    )
                }

                item {

                    ActionButtons(

                        onArm = {
                            viewModel.armDrone()
                        },

                        onDisarm = {
                            viewModel.disarmDrone()
                        },

                        onTakeoff = {
                            viewModel.takeoffDrone()
                        },

                        onRTL = {
                            viewModel.rtlDrone()
                        }
                    )
                }
            }
        }
    }
}