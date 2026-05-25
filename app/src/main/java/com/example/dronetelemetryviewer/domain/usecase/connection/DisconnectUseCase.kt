package com.example.dronetelemetryviewer.domain.usecase.connection


import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import javax.inject.Inject

class DisconnectUseCase @Inject constructor(
    private val repository: ConnectionRepository
) {

    suspend operator fun invoke() {

        repository.disconnect()
    }
}