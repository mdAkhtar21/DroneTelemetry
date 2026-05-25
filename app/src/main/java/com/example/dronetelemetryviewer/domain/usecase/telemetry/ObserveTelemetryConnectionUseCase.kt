package com.example.dronetelemetryviewer.domain.usecase.telemetry


import com.example.dronetelemetryviewer.domain.repository.TelemetryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTelemetryConnectionUseCase @Inject constructor(
    private val repository: TelemetryRepository
) {

    operator fun invoke():
            Flow<Boolean> {

        return repository.observeConnection()
    }
}