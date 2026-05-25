package com.example.dronetelemetryviewer.domain.usecase.connection


import com.example.dronetelemetryviewer.core.common.ConnectionState
import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveConnectionStateUseCase @Inject constructor(
    private val repository: ConnectionRepository
) {

    operator fun invoke():
            Flow<ConnectionState> {

        return repository.observeConnectionState()
    }
}