package ru.spbstu.lab3_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.navigation.NavigationView

class Activity2 : AppCompatActivity() {

    private lateinit var toFirstButton: Button
    private lateinit var toThirdButton: Button
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        toFirstButton = findViewById(R.id.btn_to_first)
        toThirdButton = findViewById(R.id.btn_to_third)
        navigationView = findViewById(R.id.nav_view)

        toFirstButton.setOnClickListener {
            finish()
        }
        toThirdButton.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    companion object {
        private const val SECOND_ACTIVITY_REQUEST_CODE = 1234
    }
}