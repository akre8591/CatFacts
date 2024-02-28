package com.example.technicaltest.utils

sealed class DataState<T> {
    data class Progress<T>(var loading: Boolean) : DataState<T>()
    data class Success<T>(var data: T, val requestStatus: RequestStatus = RequestStatus.SERVER) :
        DataState<T>()

    data class Failure<T>(val e: Throwable, val errorCode: Int? = null) : DataState<T>()
    class Done<T> : DataState<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): DataState<T> = Progress(isLoading)
        fun <T> success(
            data: T,
            requestStatus: RequestStatus = RequestStatus.SERVER
        ): DataState<T> = Success(data, requestStatus)

        fun <T> failure(e: Throwable, errorCode: Int? = null): DataState<T> = Failure(e, errorCode)
        fun <T> done(): DataState<T> = Done()
    }
}

enum class RequestStatus {
    SERVER,
    SYNC
}
