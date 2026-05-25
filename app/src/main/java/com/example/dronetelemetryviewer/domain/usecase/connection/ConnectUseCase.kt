package com.example.dronetelemetryviewer.domain.usecase.connection

import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import javax.inject.Inject



class ConnectUseCase @Inject constructor(
    private val repository: ConnectionRepository
) {

    suspend operator fun invoke(
        host: String,
        port: Int,
        protocol: String
    ): Boolean {

        return repository.connect(
            host,
            port,
            protocol
        )
    }
}