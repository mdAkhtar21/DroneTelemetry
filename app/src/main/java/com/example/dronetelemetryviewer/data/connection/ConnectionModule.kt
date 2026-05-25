package com.example.dronetelemetryviewer.data.connection


import com.example.dronetelemetryviewer.domain.repository.ConnectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectionModule {

    @Binds
    @Singleton
    abstract fun bindConnectionRepository(
        impl: ConnectionRepositoryImpl
    ): ConnectionRepository

    @Binds
    @Singleton
    abstract fun bindConnectionDataSource(
        impl: ConnectionDataSourceImpl
    ): ConnectionDataSource
}