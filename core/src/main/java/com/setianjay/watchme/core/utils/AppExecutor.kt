package com.setianjay.watchme.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutor @VisibleForTesting constructor(
    private val singleExecutor: Executor
) {

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor()
    )

    fun singleThread() = singleExecutor
}