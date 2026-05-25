package com.example.dronetelemetryviewer.navigation



import androidx.navigation.NavHostController

class NavigationActions(
    private val navController: NavHostController
) {

    fun navigateToDashboard() {

        navController.navigate(
            Screen.Dashboard.route
        )
    }

    fun navigateToConnection() {

        navController.navigate(
            Screen.Connection.route
        )
    }

    fun popBackStack() {

        navController.popBackStack()
    }
}