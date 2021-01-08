package ru.spbstu.lab6_1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class CoroutinesDownloadImageActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var buttonLoad: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_image)

        imageView = findViewById(R.id.iv_image)
        buttonLoad = findViewById(R.id.btn_load)
        progressBar = findViewById(R.id.progress_bar)
        buttonLoad.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                var result: Bitmap
                withContext(Dispatchers.Main) {
                    imageView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                withContext(Dispatchers.IO) {
                    URL(URL).openStream().use { input ->
                        result = BitmapFactory.decodeStream(input)
                    }
                }
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(result)
                    imageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private companion object {
        private const val URL = "https://sun9-7.userapi.com/impf/c854528/v854528268/cb5cd/rR0UHulYwUE.jpg?size=519x543&quality=96&proxy=1&sign=7ad8736c5bbd3524d6a3a45af9f7318a&type=album"
    }
}

