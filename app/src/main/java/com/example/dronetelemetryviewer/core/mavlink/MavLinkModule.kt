package com.example.dronetelemetryviewer.core.mavlink



import com.example.dronetelemetryviewer.core.network.SocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MavLinkModule {

    @Provides
    @Singleton
    fun provideMavLinkParser(): MavLinkParser {

        return MavLinkParser()
    }

    @Provides
    @Singleton
    fun provideMavLinkConnectionManager(
        socketFactory: SocketFactory
    ): MavLinkConnectionManager {

        return MavLinkConnectionManager(
            socketFactory
        )
    }

    @Provides
    @Singleton
    fun provideMavLinkTelemetryReceiver(
        connectionManager: MavLinkConnectionManager,
        parser: MavLinkParser
    ): MavLinkTelemetryReceiver {

        return MavLinkTelemetryReceiver(
            connectionManager,
            parser
        )
    }
}