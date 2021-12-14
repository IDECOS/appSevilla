package com.example.appsevilla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import android.content.Intent
import android.net.Uri




/*
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleLabel: TextView = view.findViewById(R.id.title_view_detail)
        val descriptionLabel: TextView = view.findViewById(R.id.text_description)
        //var rateLabel: TextView = view.findViewById(R.id.rate)
        val imageView: ImageView = view.findViewById(R.id.imageview_detail)
        val title = args.nameSite
        val description =  args.description
        val imageUrl = args.imageUrl

        titleLabel.text = title
        descriptionLabel.text = description

        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)

        val boton: Button = view.findViewById(R.id.ubication)
        boton.setOnClickListener {
            ubications()
        }
    }


    private fun ubications() {
        val lat = "4.4455996"
        val long = "75.127356"
        // Creates an Intent that will load a map of San Francisco
        val gmmIntentUri: Uri = Uri.parse("geo:$lat,$long")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
//        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        //}


    }

    companion object {

        @JvmStatic
        fun newInstance() = DetailFragment()
        private val TAG = DetailFragment::class.java.simpleName

    }
}