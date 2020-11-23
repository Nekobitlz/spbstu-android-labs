package ru.spbstu.lab2

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var volume = savedInstanceState?.getInt(PARAM_VOLUME) ?: 0
        btn_inc.setOnKeyListener { _, keyCode, _ ->
            return@setOnKeyListener when (keyCode) {
                KeyEvent.KEYCODE_DPAD_CENTER -> updateVolume(++volume)
                else -> false
            }
        }
        btn_dec.setOnKeyListener { _, keyCode, _ ->
            return@setOnKeyListener when (keyCode) {
                KeyEvent.KEYCODE_DPAD_CENTER -> updateVolume(--volume)
                else -> false
            }
        }
        sb_volume.setOnKeyListener { _, keyCode, _ ->
            return@setOnKeyListener when (keyCode) {
                KeyEvent.KEYCODE_DPAD_LEFT -> updateVolume(--volume)
                KeyEvent.KEYCODE_DPAD_RIGHT -> updateVolume(++volume)
                else -> false
            }
        }
    }

    private fun updateVolume(count: Int): Boolean {
        if (count in 0..100) {
            tv_count.text = count.toString()
            sb_volume.progress = count
        }
        return true
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        log("onStart()")
    }

    override fun onResume() {
        super.onResume()
        log("onResume()")
    }

    override fun onPause() {
        super.onPause()
        log("onPause()")
    }

    override fun onStop() {
        super.onStop()
        log("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState()")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        log("onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        log("onRestoreInstanceState()")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        log("onRestoreInstanceState()")
    }

    private fun log(message: String) {
        Log.d("LIFECYCLE_TAG", message)
    }

    companion object {
        private const val PARAM_VOLUME = "volume"
    }
}
