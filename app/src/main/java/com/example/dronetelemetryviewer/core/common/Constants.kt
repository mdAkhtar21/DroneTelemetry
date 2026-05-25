package com.example.dronetelemetryviewer.core.common


object Constants {

    // Default MAVLink Port
    const val DEFAULT_PORT = 14550

    // Socket timeout in milliseconds
    const val SOCKET_TIMEOUT = 5000

    // Auto reconnect delay
    const val RECONNECT_DELAY = 3000L

    // Battery warning threshold
    const val LOW_BATTERY_THRESHOLD = 20

    // Supported protocols
    const val PROTOCOL_UDP = "UDP"
    const val PROTOCOL_TCP = "TCP"

    // Connection states
    const val CONNECTION_TIMEOUT_MESSAGE =
        "Connection timeout"

    const val INVALID_HOST_MESSAGE =
        "Invalid Host Address"

    const val INVALID_PORT_MESSAGE =
        "Invalid Port"

    const val DISCONNECTED_MESSAGE =
        "Disconnected from telemetry stream"

    const val RECONNECTING_MESSAGE =
        "Reconnecting..."

    // MAVLink
    const val HEARTBEAT_TIMEOUT = 10000L

    // Logging
    const val LOG_TAG = "DroneTelemetry"

    // Retry
    const val MAX_RETRY_COUNT = 5
}