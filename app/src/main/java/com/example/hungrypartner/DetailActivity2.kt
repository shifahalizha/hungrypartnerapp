package com.example.hungrypartner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.hungrypartner.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {

    var imageURL = ""
    private lateinit var binding: ActivityDetail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null){
            binding.detailDesc.text = bundle.getString("Description")
            binding.detailTitle.text = bundle.getString("Title")
            binding.detailPriority.text = bundle.getString("Priority")
            imageURL = bundle.getString("Image")!!
            Glide.with(this).load(bundle.getString("Image")).into(binding.detailImage)
        }
    }
}