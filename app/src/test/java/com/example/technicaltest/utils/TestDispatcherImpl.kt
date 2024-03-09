package com.example.technicaltest.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherImpl : AppDispatcher {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun unconfined(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
