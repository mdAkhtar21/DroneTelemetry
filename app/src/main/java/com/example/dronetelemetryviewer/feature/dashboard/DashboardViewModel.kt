package com.example.dronetelemetryviewer.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import com.example.dronetelemetryviewer.domain.usecase.drone.ArmDroneUseCase
import com.example.dronetelemetryviewer.domain.usecase.drone.DisarmDroneUseCase
import com.example.dronetelemetryviewer.domain.usecase.drone.ReturnToLaunchUseCase
import com.example.dronetelemetryviewer.domain.usecase.drone.TakeoffDroneUseCase
import com.example.dronetelemetryviewer.domain.usecase.telemetry.ObserveTelemetryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(

    private val observeTelemetryUseCase:
    ObserveTelemetryUseCase,

    private val connectionRepository:
    ConnectionRepository,

    private val armDroneUseCase:
    ArmDroneUseCase,

    private val disarmDroneUseCase:
    DisarmDroneUseCase,

    private val takeoffDroneUseCase:
    TakeoffDroneUseCase,

    private val rtlUseCase:
    ReturnToLaunchUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            DashboardUiState(
                isLoading = true
            )
        )

    val uiState:
            StateFlow<DashboardUiState> =
        _uiState.asStateFlow()

    init {

        observeTelemetry()

        observeConnectionState()
    }

    private fun observeTelemetry() {

        viewModelScope.launch {

            observeTelemetryUseCase()
                .catch {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            error = it.message
                                ?: "Telemetry Error"
                        )
                }
                .collectLatest { telemetry ->

                    _uiState.value =
                        _uiState.value.copy(
                            telemetry = telemetry,
                            isLoading = false
                        )
                }
        }
    }

    private fun observeConnectionState() {

        viewModelScope.launch {

            connectionRepository
                .observeConnectionState()
                .collectLatest { state ->

                    _uiState.value =
                        _uiState.value.copy(
                            connectionState = state
                        )
                }
        }
    }

    fun armDrone() {

        viewModelScope.launch {

            armDroneUseCase()
        }
    }

    fun disarmDrone() {

        viewModelScope.launch {

            disarmDroneUseCase()
        }
    }

    fun takeoffDrone() {

        viewModelScope.launch {

            takeoffDroneUseCase()
        }
    }

    fun rtlDrone() {

        viewModelScope.launch {

            rtlUseCase()
        }
    }
    fun reconnect() {

        _uiState.value =
            _uiState.value.copy(
                isLoading = true
            )

        observeTelemetry()

        observeConnectionState()
    }
}