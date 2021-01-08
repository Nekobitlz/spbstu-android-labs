package ru.spbstu.lab7_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val path = intent?.getStringExtra(EXTRA_IMAGE_PATH) ?: return
                textView.text = getString(R.string.path, path)
            }
        }
        registerReceiver(receiver, IntentFilter(EXTRA_DOWNLOAD_RESULT))
        textView = findViewById(R.id.textView)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    companion object {
        const val EXTRA_DOWNLOAD_RESULT = "extra_download_result"
        const val EXTRA_IMAGE_PATH = "extra_download_path"
    }
}
