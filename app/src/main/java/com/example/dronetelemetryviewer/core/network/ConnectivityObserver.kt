package com.example.dronetelemetryviewer.core.network



import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityObserver @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    private val connectivityManager =
        context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    fun observe(): Flow<Boolean> = callbackFlow {

        val callback =
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    trySend(true)
                }

                override fun onLost(network: Network) {
                    trySend(false)
                }

                override fun onUnavailable() {
                    trySend(false)
                }
            }

        val request = NetworkRequest.Builder()
            .addCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
            .build()

        connectivityManager.registerNetworkCallback(
            request,
            callback
        )

        val activeNetwork =
            connectivityManager.activeNetwork

        val capabilities =
            connectivityManager.getNetworkCapabilities(
                activeNetwork
            )

        val isConnected =
            capabilities?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) == true

        trySend(isConnected)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(
                callback
            )
        }
    }
}