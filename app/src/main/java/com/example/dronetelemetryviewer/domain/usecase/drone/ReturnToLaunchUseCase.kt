package com.example.dronetelemetryviewer.domain.usecase.drone


import com.example.dronetelemetryviewer.domain.repository.TelemetryRepository
import javax.inject.Inject

class ReturnToLaunchUseCase @Inject constructor(
    private val repository: TelemetryRepository
) {

    suspend operator fun invoke() {

        repository.returnToLaunch()
    }
}