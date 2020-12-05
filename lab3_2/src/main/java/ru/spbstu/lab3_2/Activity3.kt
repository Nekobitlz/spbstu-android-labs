package ru.spbstu.lab3_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.navigation.NavigationView

class Activity3 : AppCompatActivity() {

    private lateinit var toFirstButton: Button
    private lateinit var toSecondButton: Button
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        toFirstButton = findViewById(R.id.btn_to_first)
        toSecondButton = findViewById(R.id.btn_to_second)
        navigationView = findViewById(R.id.nav_view)

        toFirstButton.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        toSecondButton.setOnClickListener {
            finish()
        }
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
            return@setNavigationItemSelectedListener true
        }
    }
}