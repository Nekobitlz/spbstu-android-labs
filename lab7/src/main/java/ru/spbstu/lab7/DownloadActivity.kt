package ru.spbstu.lab7

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class DownloadActivity : AppCompatActivity() {

    private lateinit var buttonLoad: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        imageView = findViewById(R.id.iv_image)
        buttonLoad = findViewById(R.id.btn_load)
        buttonLoad.setOnClickListener {
            if (isPermissionGranted()) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    WRITE_PERMISSION_REQUEST_CODE
                )
            } else {
                startService()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_PERMISSION_REQUEST_CODE
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            startService()
        }
    }

    private fun isPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
        this, Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) != PackageManager.PERMISSION_GRANTED

    private fun startService() {
        val intent = Intent(this, DownloadService::class.java).apply {
            putExtra(DownloadService.EXTRA_URL, URL)
        }
        startService(intent)
    }

    private companion object {
        private const val WRITE_PERMISSION_REQUEST_CODE = 1234
        private const val URL =
            "https://sun9-7.userapi.com/impf/c854528/v854528268/cb5cd/rR0UHulYwUE.jpg?size=519x543&quality=96&proxy=1&sign=7ad8736c5bbd3524d6a3a45af9f7318a&type=album"
    }
}