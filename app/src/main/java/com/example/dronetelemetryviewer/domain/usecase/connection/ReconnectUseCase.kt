package com.example.dronetelemetryviewer.domain.usecase.connection


import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import javax.inject.Inject

class ReconnectUseCase @Inject constructor(
    private val repository: ConnectionRepository
) {

    suspend operator fun invoke(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        return repository.reconnect(
            host,
            port,
            protocol
        )
    }
}