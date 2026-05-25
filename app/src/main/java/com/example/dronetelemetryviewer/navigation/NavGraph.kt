package com.example.dronetelemetryviewer.navigation



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dronetelemetryviewer.feature.connection.ConnectionScreen
import com.example.dronetelemetryviewer.feature.dashboard.DashboardScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Connection.route,
        modifier = modifier
    ) {

        composable(
            route = Screen.Connection.route
        ) {

            ConnectionScreen(

                onConnected = {

                    navController.navigate(
                        Screen.Dashboard.route
                    ) {

                        popUpTo(
                            Screen.Connection.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Dashboard.route
        ) {

            DashboardScreen()
        }
    }
}