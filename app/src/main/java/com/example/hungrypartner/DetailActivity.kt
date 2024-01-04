package com.example.hungrypartner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getData = intent.getParcelableExtra<DataClass>("android")
        if(getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailIngredients: TextView = findViewById(R.id.detailIngredients)
            val detailImage: ImageView = findViewById(R.id.detailImage)

            detailTitle.text = getData.dataTitle
            detailDesc.text = getData.dataDesc
            detailIngredients.text = getData.dataIngredients
            detailImage.setImageResource(getData.dataDetailImage)


        }


    }
}