package com.example.appsevilla.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.appsevilla.R
import com.example.appsevilla.viewmodel.SiteViewModel



/*
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {


    private lateinit var siteViewModel: SiteViewModel
    private lateinit var titleLabel: TextView
    private lateinit var image: ImageView
    private lateinit var descriptionLabel: TextView
    private lateinit var temperatureLabel: TextView
    private lateinit var qualificationLabel: TextView

    private lateinit var title: String
    private lateinit var descrption: String
    private lateinit var imageUrl: String
    private lateinit var geo: String
    private lateinit var temperature: String
    private var qualification: Int = 0

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

        title = args.nameSite
        descrption = args.description
        imageUrl = args.imageUrl
        geo = args.geo
        temperature = args.temperature
        qualification = args.qualification

        siteViewModel = ViewModelProvider(requireActivity()).get(SiteViewModel::class.java)
        updateDisplay(view)
        val boton: Button = view.findViewById(R.id.ubication)
        boton.setOnClickListener {
            ubications()
        }
    }

    private fun ubications() {

        // Creates an Intent that will load a map of San Francisco
        val gmmIntentUri: Uri = Uri.parse("geo:$geo")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun updateDisplay(view: View){
        titleLabel = view.findViewById<TextView>(R.id.title_view_detail).apply{
            text = title
        }
        descriptionLabel = view.findViewById<TextView>(R.id.text_description).apply {
            text = descrption
        }
        temperatureLabel = view.findViewById<TextView>(R.id.temperature).apply{
            text = temperature
        }
        image = view.findViewById(R.id.imageview_detail);
        Glide.with(this)
            .load(imageUrl)
            .into(image)

    }

    companion object {

        @JvmStatic
        fun newInstance() = DetailFragment()
        private val TAG = DetailFragment::class.java.simpleName

    }
}