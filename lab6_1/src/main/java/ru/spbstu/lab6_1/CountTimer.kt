package ru.spbstu.lab6_1

import android.util.Log
import kotlin.system.measureTimeMillis

class CountTimer {

    private var startTime = System.currentTimeMillis()
    private var delayError = 0L

    fun reset() {
        startTime = System.currentTimeMillis()
        delayError = 0L
    }

    fun startTask(task: (delay: Long) -> Unit) {
        val delta = System.currentTimeMillis() - startTime
        val delay = 1000L - delayError - delta
        val realDelay = measureTimeMillis { task(delay) }
        Log.d(javaClass.simpleName, "Delay: $realDelay")
        delayError = realDelay - delay
        startTime = System.currentTimeMillis()
    }
}