package com.example.appsevilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra(KEY_NAME)
        val description = intent.getStringExtra(KEY_DESCRIPTION)
        val rate = intent.getStringExtra(KEY_RATE)
        var imageUrl = intent.getStringExtra(KEY_IMAGE)

        val title: TextView = findViewById<TextView>(R.id.title_view_detail).apply {
            name.also { it.also { text.it } }
        }
        var imageviewDetail: ImageView = findViewById<ImageView>(R.id.imageview_detail).apply {
            imageUrl.also { it.also { text.it } }
        val descriptionText: TextView = findViewById<TextView>(R.id.text_description).apply {
            imageUrl.also { it.also { text.it } }


    }
}