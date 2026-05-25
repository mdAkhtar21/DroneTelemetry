package com.example.dronetelemetryviewer.core.mavlink

import com.example.dronetelemetryviewer.core.network.SocketFactory
import io.dronefleet.mavlink.MavlinkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramSocket
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MavLinkConnectionManager @Inject constructor(
    private val socketFactory: SocketFactory
) {

    private var mavlinkConnection: MavlinkConnection? = null

    private var udpSocket: DatagramSocket? = null

    private var tcpSocket: Socket? = null

    suspend fun connectUdp(
        port: Int
    ): Boolean {

        return withContext(Dispatchers.IO) {

            try {

                udpSocket =
                    socketFactory.createUdpSocket(port)

                // UDP socket created successfully
                // MAVLink UDP parsing can be implemented later

                true

            } catch (e: Exception) {

                e.printStackTrace()

                false
            }
        }
    }

    suspend fun connectTcp(
        host: String,
        port: Int
    ): Boolean {

        return withContext(Dispatchers.IO) {

            try {

                tcpSocket =
                    socketFactory.createTcpSocket(
                        host,
                        port
                    )

                mavlinkConnection =
                    MavlinkConnection.create(
                        tcpSocket!!.getInputStream(),
                        tcpSocket!!.getOutputStream()
                    )

                true

            } catch (e: Exception) {

                e.printStackTrace()

                false
            }
        }
    }

    fun getConnection(): MavlinkConnection? {

        return mavlinkConnection
    }

    fun isConnected(): Boolean {

        return when {

            tcpSocket != null ->
                tcpSocket!!.isConnected

            udpSocket != null ->
                !udpSocket!!.isClosed

            else -> false
        }
    }

    suspend fun disconnect() {

        withContext(Dispatchers.IO) {

            try {

                tcpSocket?.close()

                udpSocket?.close()

                mavlinkConnection = null

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}