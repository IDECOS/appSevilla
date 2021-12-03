package com.example.appsevillafragments


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SitiosAdapter(
    private val listSites: ArrayList<SitioSevilla>,
    private val onClick: (SitioSevilla) -> Unit
): RecyclerView.Adapter<SitiosAdapter.SitiosViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitiosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_site, parent, false)
        return SitiosViewHolder(view)
    }

    override fun onBindViewHolder(holder: SitiosViewHolder, position: Int) {
        val miSitio = listSites[position]
            holder.bind(miSitio = miSitio)
    }

    override fun getItemCount(): Int {
        return listSites.size
    }

    inner class SitiosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var titleLabel: TextView = itemView.findViewById(R.id.title_view)
        private var descriptionLabel: TextView = itemView.findViewById(R.id.description)
        private var rateLabel: TextView = itemView.findViewById(R.id.rate)
        private var imageView: ImageView = itemView.findViewById(R.id.image_item)
        private var currentSite: SitioSevilla? = null

        init {
            itemView.setOnClickListener {
              currentSite?.let {
                  onClick(it)
              }
            }
        }

        fun bind(miSitio: SitioSevilla){
            currentSite = miSitio

            titleLabel.text = miSitio.nameSite
            descriptionLabel.text = miSitio.description
            rateLabel.text = miSitio.rate

            Glide.with(itemView)
                .load(miSitio.imageUrl)
                .into(imageView)
        }

    }

}

