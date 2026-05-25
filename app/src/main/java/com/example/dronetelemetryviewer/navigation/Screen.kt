package com.example.dronetelemetryviewer.navigation


sealed class Screen(
    val route: String
) {

    data object Connection :
        Screen("connection")

    data object Dashboard :
        Screen("dashboard")
}