package com.example.hungrypartner


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class IntroActivity : AppCompatActivity() {

lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnSubmit = findViewById(R.id.btn_submit)
        btnSubmit.setOnClickListener {
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)

        }



    }
}