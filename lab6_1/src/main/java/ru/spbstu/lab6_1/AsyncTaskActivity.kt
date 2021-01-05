package ru.spbstu.lab6_1

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AsyncTaskActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed = 0

    private var backgroundThread: AsyncTask<Unit, Unit, Unit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        super.onResume()
        backgroundThread = CountTimerAsyncTask { display() }
        backgroundThread?.execute()
    }

    override fun onPause() {
        backgroundThread?.cancel(true)
        backgroundThread = null
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PARAM_SECONDS_ELAPSED, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(PARAM_SECONDS_ELAPSED)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun display() {
        textSecondsElapsed.text = resources.getString(R.string.seconds_elapsed, secondsElapsed++)
    }

    private class CountTimerAsyncTask(
        private val task: () -> Unit
    ) : AsyncTask<Unit, Unit, Unit>() {
        private val timer = CountTimer()

        override fun doInBackground(vararg params: Unit?) {
            while (!isCancelled) {
                timer.startTask { Thread.sleep(it) }
                publishProgress()
            }
        }

        override fun onProgressUpdate(vararg values: Unit?) {
            task()
        }
    }

    companion object {
        private const val PARAM_SECONDS_ELAPSED = "PARAM_SECONDS_ELAPSED"
    }
}
