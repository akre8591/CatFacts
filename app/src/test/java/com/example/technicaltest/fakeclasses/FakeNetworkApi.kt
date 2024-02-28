package com.example.technicaltest.fakeclasses

import com.example.technicaltest.utils.NetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNetworkApi : NetworkApi {

    var networkAvailable = true
    var wifiEnabled = true
    var mobileNetworkEnabled = true

    val connectivityStatusFlow = MutableStateFlow(NetworkApi.Status.Unavailable)

    override fun isNetworkAvailable(): Boolean = networkAvailable

    override fun isWifiEnabled(): Boolean = wifiEnabled

    override fun isMobileNetworkEnabled(): Boolean = mobileNetworkEnabled

    override fun connectivityStatus(): Flow<NetworkApi.Status> = connectivityStatusFlow
}
