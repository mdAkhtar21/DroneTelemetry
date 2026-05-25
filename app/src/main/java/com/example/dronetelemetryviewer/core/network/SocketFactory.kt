package com.example.dronetelemetryviewer.core.network


import com.example.dronetelemetryviewer.core.common.Constants
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketFactory @Inject constructor() {

    fun createUdpSocket(
        port: Int
    ): DatagramSocket {

        val socket = DatagramSocket(null)

        socket.reuseAddress = true

        socket.soTimeout = Constants.SOCKET_TIMEOUT

        socket.bind(
            InetSocketAddress(port)
        )

        return socket
    }

    fun createTcpSocket(
        host: String,
        port: Int
    ): Socket {

        val socket = Socket()

        socket.soTimeout = Constants.SOCKET_TIMEOUT

        socket.connect(
            InetSocketAddress(host, port),
            Constants.SOCKET_TIMEOUT
        )

        return socket
    }
}