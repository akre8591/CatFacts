package com.example.technicaltest.utils

import kotlinx.coroutines.flow.Flow

interface NetworkApi {
    fun isNetworkAvailable(): Boolean

    fun isWifiEnabled(): Boolean

    fun isMobileNetworkEnabled(): Boolean

    fun connectivityStatus(): Flow<Status>

    enum class Status {
        Available, Unavailable, Lost, Losing
    }
}
