package com.example.appsevilla.ui

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
import androidx.lifecycle.ViewModelProvider
import com.example.appsevilla.R
import com.example.appsevilla.viewmodel.SiteViewModel


/*
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    private lateinit var siteViewModel: SiteViewModel
    private lateinit var title_label: TextView
    private lateinit var image: ImageView
    private lateinit var description_label: TextView

    //val args: DetailFragmentArgs by navArgs()

    private var geo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title_label = view.findViewById(R.id.title_view_detail)
        description_label = view.findViewById(R.id.text_description)
        image = view.findViewById(R.id.imageview_detail)
        siteViewModel = ViewModelProvider(requireActivity()).get(SiteViewModel::class.java)
        observeLiveData()
        val boton: Button = view.findViewById(R.id.ubication)
        boton.setOnClickListener {
            ubications()
        }
    }

    private fun observeLiveData() {
        siteViewModel.getSiteObserver().observe(viewLifecycleOwner, { site ->
            title_label.text = site.name
            description_label.text = site.description
            geo = site.geo

            Glide.with(this)
            .load(site.image)
            .into(image)

        })
    }

    private fun ubications() {

        // Creates an Intent that will load a map of San Francisco
        val gmmIntentUri: Uri = Uri.parse("geo:$geo")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    companion object {

        @JvmStatic
        fun newInstance() = DetailFragment()
        private val TAG = DetailFragment::class.java.simpleName

    }
}