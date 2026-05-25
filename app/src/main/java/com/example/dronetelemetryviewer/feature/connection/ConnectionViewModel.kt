package com.example.dronetelemetryviewer.feature.connection



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dronetelemetryviewer.domain.usecase.connection.ConnectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
    private val connectUseCase: ConnectUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(ConnectionUiState())

    val uiState: StateFlow<ConnectionUiState> =
        _uiState.asStateFlow()

    fun onEvent(event: ConnectionEvent) {

        when (event) {

            is ConnectionEvent.HostChanged -> {

                _uiState.value =
                    _uiState.value.copy(
                        host = event.host
                    )
            }

            is ConnectionEvent.PortChanged -> {

                _uiState.value =
                    _uiState.value.copy(
                        port = event.port
                    )
            }

            is ConnectionEvent.ProtocolChanged -> {

                _uiState.value =
                    _uiState.value.copy(
                        protocol = event.protocol
                    )
            }

            ConnectionEvent.ConnectClicked -> {

                connect()
            }
        }
    }

    private fun connect() {

        val host = _uiState.value.host

        val port =
            _uiState.value.port.toIntOrNull()

        if (host.isBlank() || port == null) {

            _uiState.value =
                _uiState.value.copy(
                    error = "Invalid host or port"
                )

            return
        }

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    error = null
                )

            try {

                val connected =
                    connectUseCase(
                        host,
                        port,
                        _uiState.value.protocol
                    )

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        isConnected = connected
                    )

            } catch (e: Exception) {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
            }
        }
    }
}