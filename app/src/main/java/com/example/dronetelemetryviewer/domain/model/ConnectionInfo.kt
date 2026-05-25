package com.example.dronetelemetryviewer.domain.model


data class ConnectionInfo(

    val host: String,

    val port: Int,

    val protocol: String
)