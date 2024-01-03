package com.example.hungrypartner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                },2000

            )
    }
}