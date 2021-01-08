package ru.spbstu.lab7

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.MediaStore.Images.Media.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.spbstu.lab7.Consts.EXTRA_DOWNLOAD_RESULT
import ru.spbstu.lab7.Consts.EXTRA_IMAGE_PATH
import java.io.IOException
import java.net.URL

class DownloadService : Service() {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
        val notification = getNotification("Downloading...", "Image is loading", true)
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra(EXTRA_URL)
        if (url != null) {
            scope.launch {
                val image = getDownloadedImage(url)
                val path = saveImage(image)
                val resultIntent = Intent(EXTRA_DOWNLOAD_RESULT).apply {
                    putExtra(EXTRA_IMAGE_PATH, path)
                }
                sendBroadcast(resultIntent)
                stopSelf(startId)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun getDownloadedImage(url: String): Bitmap? {
        var bitmap: Bitmap?
        URL(url).openStream().use {
            bitmap = BitmapFactory.decodeStream(it)
        }
        return bitmap
    }

    private fun saveImage(bitmap: Bitmap?): String? {
        val manager = NotificationManagerCompat.from(this)
        var item: Uri? = null
        var notification: Notification

        try {
            requireNotNull(bitmap)
            val filename = "image_${bitmap.generationId}.png"
            val values = ContentValues().apply {
                put(DISPLAY_NAME, filename)
                put(MIME_TYPE, "image/png")
            }
            item = contentResolver.insert(EXTERNAL_CONTENT_URI, values) ?: throw IOException()

            val openOutputStream = contentResolver.openOutputStream(item) ?: throw IOException()
            openOutputStream.use {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) throw IOException()
            }
            notification = getNotification("Image downloaded successfully on path", "${item.path}", false)
        } catch (e: Exception) {
            if (item != null) {
                contentResolver.delete(item, null, null)
            }
            notification = getNotification("Error", "Failed to download image", false)
        }
        stopForeground(true)
        manager.notify(NOTIFICATION_ID, notification)
        return item?.path
    }

    private fun getNotification(
        title: String,
        contentText: String,
        ongoing: Boolean
    ): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_menu_upload)
            .setOngoing(ongoing)
            .build()
    }

    companion object {
        const val EXTRA_URL = "extra_url"
        private const val CHANNEL_ID = "download_channel"
        private const val CHANNEL_NAME = "Downloading Status"
        private const val NOTIFICATION_ID = 1
    }
}