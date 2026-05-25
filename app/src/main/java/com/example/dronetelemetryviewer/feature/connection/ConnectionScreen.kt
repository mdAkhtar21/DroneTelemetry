package com.example.dronetelemetryviewer.feature.connection



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ConnectionScreen(
    onConnected: () -> Unit,
    viewModel: ConnectionViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    if (state.isConnected) {
        onConnected()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(top=40.dp, start = 16.dp,end=16.dp),
        topBar = {
            Text(
                text = "Drone Telemetry Connection",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = state.host,
                onValueChange = {
                    viewModel.onEvent(
                        ConnectionEvent.HostChanged(it)
                    )
                },
                label = {
                    Text("IP Address")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.port,
                onValueChange = {
                    viewModel.onEvent(
                        ConnectionEvent.PortChanged(it)
                    )
                },
                label = {
                    Text("Port")
                },
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = {
                    viewModel.onEvent(
                        ConnectionEvent.ProtocolChanged(
                            if (state.protocol == "UDP")
                                "TCP"
                            else
                                "UDP"
                        )
                    )
                }
            ) {

                Text(
                    text = "Protocol: ${state.protocol}"
                )
            }

            Button(
                onClick = {
                    viewModel.onEvent(
                        ConnectionEvent.ConnectClicked
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Connect")
            }

            if (state.isLoading) {

                CircularProgressIndicator(
                    modifier= Modifier.align(
                        CenterHorizontally
                    )
                )
            }

            state.error?.let {

                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }


}