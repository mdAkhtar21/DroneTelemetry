package com.example.dronetelemetryviewer.domain.usecase.drone


import com.example.dronetelemetryviewer.domain.repository.TelemetryRepository
import javax.inject.Inject

class TakeoffDroneUseCase @Inject constructor(
    private val repository: TelemetryRepository
) {

    suspend operator fun invoke() {

        repository.takeoff()
    }
}