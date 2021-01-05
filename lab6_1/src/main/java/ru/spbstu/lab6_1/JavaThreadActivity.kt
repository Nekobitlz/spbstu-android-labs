package ru.spbstu.lab6_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JavaThreadActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed = 0

    private var backgroundThread: Thread? = null
    private val timer: CountTimer = CountTimer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        super.onResume()
        backgroundThread = createThread()
        backgroundThread?.start()
    }

    override fun onPause() {
        backgroundThread?.interrupt()
        backgroundThread = null
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PARAM_SECONDS_ELAPSED, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(PARAM_SECONDS_ELAPSED)
        if (secondsElapsed > 0) {
            display()
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun createThread() = Thread {
        try {
            timer.reset()
            while (!Thread.interrupted()) {
                timer.startTask { Thread.sleep(it) }
                display()
            }
        } catch (e: InterruptedException) {
            // ignore
        }
    }

    private fun display() {
        textSecondsElapsed.post {
            textSecondsElapsed.text = resources.getString(R.string.seconds_elapsed, ++secondsElapsed)
        }
    }

    companion object {
        private const val PARAM_SECONDS_ELAPSED = "PARAM_SECONDS_ELAPSED"
    }
}
