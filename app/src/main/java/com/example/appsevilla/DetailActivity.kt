package com.example.appsevilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra(EXTRA_MESSAGE)
        val description = intent.getStringExtra(EXTRA_MESSAGE)
        //val imageUrl = intent.getStringExtra(EXTRA_MESSAGE)

        val titleView: TextView = findViewById<TextView>(R.id.title_view_detail).apply {
            name.also {
                it.also {
                    text = it
                }
            }
        }
        /*val descriptionView: TextView = findViewById<TextView>(R.id.text_description).apply {
            description.also {
                it.also {
                    text = it
                }
            }
        }*/
        /*val imageView: ImageView = findViewById(R.id.imageview_detail).apply {
            imageUrl.also {
                it.also {
                    text.it
                }
            }
        }*/


    }
}