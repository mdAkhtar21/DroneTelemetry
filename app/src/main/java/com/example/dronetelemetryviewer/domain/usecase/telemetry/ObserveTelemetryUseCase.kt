package com.example.dronetelemetryviewer.domain.usecase.telemetry


import com.example.dronetelemetryviewer.domain.model.TelemetryData
import com.example.dronetelemetryviewer.domain.repository.TelemetryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTelemetryUseCase @Inject constructor(
    private val repository: TelemetryRepository
) {

    operator fun invoke():
            Flow<TelemetryData> {

        return repository.observeTelemetry()
    }
}