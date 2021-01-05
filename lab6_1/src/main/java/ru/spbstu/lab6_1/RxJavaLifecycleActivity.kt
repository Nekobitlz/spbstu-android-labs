package ru.spbstu.lab6_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.trello.rxlifecycle4.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class RxJavaLifecycleActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        super.onResume()
        Observable.interval(1, TimeUnit.SECONDS)
            .bindUntilEvent(this, Lifecycle.Event.ON_PAUSE)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { display() }
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

    private fun display() {
        textSecondsElapsed.text = resources.getString(R.string.seconds_elapsed, ++secondsElapsed)
    }

    companion object {
        private const val PARAM_SECONDS_ELAPSED = "PARAM_SECONDS_ELAPSED"
    }
}
