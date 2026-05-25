package com.example.dronetelemetryviewer.core.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusIndicator(
    isConnected: Boolean,
    batteryLevel: Int
) {

    val statusColor = when {

        !isConnected -> Color.Red

        batteryLevel <= 20 -> Color(0xFFFF9800)

        else -> Color.Green
    }

    val statusText = when {

        !isConnected -> "Disconnected"

        batteryLevel <= 20 -> "Low Battery"

        else -> "Connected"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(MaterialTheme.shapes.small)
                .background(statusColor)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = statusText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}