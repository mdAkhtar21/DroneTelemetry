package com.example.dronetelemetryviewer.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigator() {

    val navController =
        rememberNavController()

    NavGraph(
        navController = navController
    )
}