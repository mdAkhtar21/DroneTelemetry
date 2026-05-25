package com.example.dronetelemetryviewer.feature.dashboard



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ActionButtons(
    onArm: () -> Unit,
    onDisarm: () -> Unit,
    onTakeoff: () -> Unit,
    onRTL: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceEvenly
    ) {

        Button(onClick = onArm) {
            Text("Arm")
        }

        Button(onClick = onDisarm) {
            Text("Disarm")
        }

        Button(onClick = onTakeoff) {
            Text("Takeoff")
        }

        Button(onClick = onRTL) {
            Text("RTL")
        }
    }
}