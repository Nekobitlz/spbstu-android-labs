package ru.spbstu.lab6_1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class AsyncDownloadImageActivity : AppCompatActivity() {

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
            DownloadImageTask(imageView, progressBar).execute(URL)
        }
    }

    class DownloadImageTask(
        private val imageView: ImageView,
        private val progressBar: ProgressBar
    ) : AsyncTask<String, Unit, Bitmap>() {

        override fun onPreExecute() {
            imageView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg parameters: String): Bitmap? {
            if (parameters.isEmpty()) {
                throw Exception("Image URL not specified!")
            }

            val url = parameters.first()
            var bitmap: Bitmap

            URL(url).openStream().use {
                bitmap = BitmapFactory.decodeStream(it)
            }

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result == null) {
                return
            }
            imageView.setImageBitmap(result)
            imageView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private companion object {
        private const val URL =
            "https://sun9-7.userapi.com/impf/c854528/v854528268/cb5cd/rR0UHulYwUE.jpg?size=519x543&quality=96&proxy=1&sign=7ad8736c5bbd3524d6a3a45af9f7318a&type=album"
    }
}

