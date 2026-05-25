package com.example.dronetelemetryviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.dronetelemetryviewer.navigation.NavGraph
import com.example.dronetelemetryviewer.ui.theme.DroneTelemetryViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            DroneTelemetryViewerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val navController =
                        rememberNavController()

                    NavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}