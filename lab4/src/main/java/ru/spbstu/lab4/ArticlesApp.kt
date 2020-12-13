package ru.spbstu.lab4

import android.app.Application
import name.ank.lab4.BibConfig
import name.ank.lab4.BibDatabase
import java.io.InputStreamReader

class ArticlesApp : Application() {

    lateinit var database: BibDatabase

    override fun onCreate() {
        super.onCreate()
        val articlesStream = resources.openRawResource(R.raw.mixed)
        val databaseConfig = BibConfig().apply {
            shuffle = true
            strict = true
        }
        InputStreamReader(articlesStream).use { articlesReader ->
            database = BibDatabase(articlesReader, databaseConfig)
        }
    }
}