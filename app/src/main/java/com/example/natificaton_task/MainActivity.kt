package com.example.natificaton_task

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var bt1: Button
    lateinit var bt2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt1.setOnClickListener {
            localHelper().setLocal(this, "en")
            startActivity(Intent(this, result::class.java))
            finish()
        }
        bt2.setOnClickListener {
            localHelper().setLocal(this, "ar")
            startActivity(Intent(this, result::class.java))
            finish()
        }
    }

}