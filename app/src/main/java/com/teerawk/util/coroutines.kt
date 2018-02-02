package com.teerawk.util

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

fun BG(block: suspend () -> Unit): Job {
    return launch(CommonPool) {
        block.invoke()
    }
}

fun UI(block: suspend () -> Unit): Job {
    return launch(kotlinx.coroutines.experimental.android.UI) {
        block.invoke()
    }
}