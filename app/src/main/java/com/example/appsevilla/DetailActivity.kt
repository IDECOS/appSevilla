package com.example.appsevilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val message = intent.getStringExtra()

        val title: TextView = findViewById(R.id.title_view_detail)
        var imageviewDetail: ImageView = findViewById(R.id.imageview_detail)
        val description: TextView = findViewById(R.id.text_description)

    }
}