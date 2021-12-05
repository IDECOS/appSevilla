package com.example.appsevilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra(MainActivity.KEY_NAME)
        val description = intent.getStringExtra(MainActivity.KEY_DESCRIPTION)
        val image = intent.getStringExtra(MainActivity.KEY_IMAGE)

        var titleLabel: TextView = findViewById(R.id.title_view_detail)
        var descriptionLabel: TextView = findViewById(R.id.text_description)
        //var rateLabel: TextView = itemView.findViewById(R.id.rate)
        var imageView: ImageView = findViewById(R.id.imageview_detail)
        var currentSite: SitioSevilla? = null

        titleLabel.text = title
        descriptionLabel.text = description

        Glide.with(imageView)
            .load(image)
            .into(imageView)


        Log.d(TAG, "$title")
        Log.d(TAG, "$description")
        Log.d(TAG, "$image")


    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}