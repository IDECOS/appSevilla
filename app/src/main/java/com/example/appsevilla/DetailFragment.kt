package com.example.appsevilla

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = requireContext().getString(R.string.title)
        val description = requireContext().getString(R.string.description)
        val image = requireContext().getString(R.string.site_thumb)

        val titleLabel: TextView = view.findViewById(R.id.title_view_detail)
        val descriptionLabel: TextView = view.findViewById(R.id.text_description)
        var rateLabel: TextView = view.findViewById(R.id.rate)
        val imageView: ImageView = view.findViewById(R.id.imageview_detail)

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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = DetailFragment()
        private val TAG = DetailFragment::class.java.simpleName

    }
}