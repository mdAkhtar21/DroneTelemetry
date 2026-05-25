package com.example.dronetelemetryviewer.core.network



import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSocketFactory(): SocketFactory {
        return SocketFactory()
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {

        return ConnectivityObserver(context)
    }
}