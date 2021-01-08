package ru.spbstu.lab6_1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.view.SimpleDraweeView

class FrescoDownloadImageActivity : AppCompatActivity() {

    private lateinit var buttonLoad: Button
    private lateinit var imageView: SimpleDraweeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco_download)

        imageView = findViewById(R.id.iv_image)
        buttonLoad = findViewById(R.id.btn_load)
        buttonLoad.setOnClickListener {
            imageView.setImageURI(URL)
        }
    }

    private companion object {
        private const val URL = "https://sun9-7.userapi.com/impf/c854528/v854528268/cb5cd/rR0UHulYwUE.jpg?size=519x543&quality=96&proxy=1&sign=7ad8736c5bbd3524d6a3a45af9f7318a&type=album"
    }
}