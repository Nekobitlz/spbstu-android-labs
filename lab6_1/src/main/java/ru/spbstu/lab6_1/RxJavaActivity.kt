package ru.spbstu.lab6_1

import io.reactivex.rxjava3.disposables.CompositeDisposable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class RxJavaActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed = 0
    private val disposable = AutoDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        disposable.bindTo(lifecycle)
    }

    override fun onResume() {
        super.onResume()
        disposable.add(Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { display() }
        )
    }

    override fun onPause() {
        super.onPause()
       // disposable.clear()
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