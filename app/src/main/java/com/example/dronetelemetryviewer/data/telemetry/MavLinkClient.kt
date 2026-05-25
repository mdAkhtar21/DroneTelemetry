package com.example.dronetelemetryviewer.data.telemetry

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.net.Socket

class MavLinkClient {

    private var udpSocket: DatagramSocket? = null
    private var tcpSocket: Socket? = null

    suspend fun connectUdp(port: Int) {
        withContext(Dispatchers.IO) {
            udpSocket = DatagramSocket(null)
            udpSocket?.reuseAddress = true
            udpSocket?.bind(InetSocketAddress(port))
        }
    }

    suspend fun connectTcp(host: String, port: Int) {
        withContext(Dispatchers.IO) {
            tcpSocket = Socket(host, port)
        }
    }
}