package com.example.appsevilla.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsevilla.R
import com.example.appsevilla.model.SitePoi

class SitiosAdapter : RecyclerView.Adapter<SitiosAdapter.SitiosViewHolder>() {

    private var listSites = mutableListOf<SitePoi>()
    private lateinit var mListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitiosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_site, parent, false)
        return SitiosViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: SitiosViewHolder, position: Int) {
        val miSitio = listSites[position]
        holder.bind(miSitio = miSitio)
    }

    override fun getItemCount(): Int {
        return listSites.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, data: SitePoi)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdateSite(places: ArrayList<SitePoi>) {
        this.listSites = places
        notifyDataSetChanged()
    }

    inner class SitiosViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        private var titleLabel: TextView = itemView.findViewById(R.id.title_view)
        private var descriptionLabel: TextView = itemView.findViewById(R.id.description)
        private var imageView: ImageView = itemView.findViewById(R.id.imageview_thumb)
        private var rateLabel: TextView = itemView.findViewById(R.id.rate)
        private var currentSite: SitePoi? = null

        init {
            itemView.setOnClickListener {
               listener.onItemClick(adapterPosition, listSites[position])
            }
        }

        fun bind(miSitio: SitePoi){
            currentSite = miSitio

            titleLabel.text = miSitio.name
            descriptionLabel.text = miSitio.description
            rateLabel.text = miSitio.qualification.toString()

            Glide.with(itemView)
                .load(miSitio.image)
                .into(imageView)
        }
    }


}