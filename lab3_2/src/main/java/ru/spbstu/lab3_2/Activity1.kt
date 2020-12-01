package ru.spbstu.lab3_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.navigation.NavigationView

class Activity1 : AppCompatActivity() {

    private lateinit var toSecondButton: Button
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        toSecondButton = findViewById(R.id.btn_to_second)
        navigationView = findViewById(R.id.nav_view)

        toSecondButton.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            return@setNavigationItemSelectedListener true
        }
    }
}
