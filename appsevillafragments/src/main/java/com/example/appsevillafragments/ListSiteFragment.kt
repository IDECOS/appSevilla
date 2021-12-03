package com.example.appsevillafragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListSiteFragment : Fragment() {

    private lateinit var listSites: ArrayList<SitioSevilla>
    private lateinit var siteAdapter: SitiosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_site, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_recycle)

        val layoutManager = LinearLayoutManager(requireContext())

        recyclerView.layoutManager = layoutManager

        setupRecycleView()

    }

    private fun setupRecycleView() {
        //listSites = arrayListOf()
        listSites = createMockContacts()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        siteAdapter = SitiosAdapter(listSites) { misitio ->
            misitioOnClick(misitio)
        }
        recyclerView.adapter = siteAdapter
    }

    private fun misitioOnClick(misitio: SitioSevilla) {
        Log.d(TAG, "Click en ${misitio.nameSite}")

    }

    private fun createMockContacts(): ArrayList<SitioSevilla> {
        return arrayListOf(
            SitioSevilla(
                "Cafe Palomino",
                "Sitio de gran concurrencia",
                "5",
                "https://encolombia.com/wp-content/uploads/2012/12/cafeteria-1-330x205.jpg"
            ),
            SitioSevilla(
                "Paramo",
                "Sitio turistico frio",
                "5",
                "https://www.semana.com/resizer/_vO2-NbNOLgtaZ3bW128-KMPc1Q=/1200x675/filters:format(jpg):quality(50)//cloudfront-us-east-1.images.arcpublishing.com/semana/4ZVKNXJ6SVDQLFVMRDVIOYUASQ.jpg"
            )
        )
    }

    companion object {
        private val TAG = ListSiteFragment::class.java.simpleName
//        const val KEY_NAME = "name_extra_title"
//        const val KEY_DESCRIPTION = "name_extra_description"
//        const val KEY_IMAGE = "name_extra_imageUrl"
    }


}