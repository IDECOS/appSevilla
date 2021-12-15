package com.example.appsevilla


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class ListSiteFragment : Fragment() {

    private lateinit var listSites: ArrayList<SitioSevilla>
    private lateinit var siteAdapter: SitiosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_list_site, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_recycle)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        setupRecycleView()
        generateSites()

    }

    private fun setupRecycleView() {
        listSites = arrayListOf()
        //listSites = createMockContacts()
        with(recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        siteAdapter = SitiosAdapter(listSites, requireContext()) { misitio ->
            view?.let { misitioOnClick(misitio, it) }
        }
        recyclerView.adapter = siteAdapter
    }

    private fun misitioOnClick(misitio: SitioSevilla, view: View) {
        Log.d(TAG, "Click en ${misitio.nameSite}")

        val action = ListSiteFragmentDirections
            .actionListSiteFragmentToDetailFragment(misitio.nameSite,
                misitio.description,
                misitio.imageUrl
            )
        Navigation.findNavController((view)).navigate(action)

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun generateSites() {
        val sitioString = readSitesFromJsonFile()
        try {
            val sitiosJson = JSONArray(sitioString)
            for (i in 0 until sitiosJson.length()) {
                val sitioJson = sitiosJson.getJSONObject(i)
                val sitioSevilla = SitioSevilla(
                    sitioJson.optInt(id.toString()),
                    sitioJson.getString("name"),
                    sitioJson.getString("description"),
                    sitioJson.getString("imageUrl"),
                    sitioJson.getString("geo"),
                    sitioJson.getString("temperature"),
                    sitioJson.getString("rate")

                )
                Log.d(TAG, "generateSites: $sitioSevilla")
                listSites.add(sitioSevilla)
            }
            siteAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun readSitesFromJsonFile(): String? {
        var sitesSevillaString: String? = null
        try {
            val inputStream = requireActivity().assets.open("site_sevilla.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            sitesSevillaString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sitesSevillaString
    }


//    private fun createMockContacts(): ArrayList<SitioSevilla> {
//        return arrayListOf(
//            SitioSevilla(
//                    1,
//                "Cafe Palomino",
//                "Sitio de gran concurrencia",
//                "5",
//                "https://encolombia.com/wp-content/uploads/2012/12/cafeteria-1-330x205.jpg"
//            ),
//            SitioSevilla(
//                2,
//                "Paramo",
//                "Sitio turistico frio",
//                "5",
//                "https://www.semana.com/resizer/_vO2-NbNOLgtaZ3bW128-KMPc1Q=/1200x675/filters:format(jpg):quality(50)//cloudfront-us-east-1.images.arcpublishing.com/semana/4ZVKNXJ6SVDQLFVMRDVIOYUASQ.jpg"
//            )
//        )
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater ) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fm: FragmentManager = requireActivity().supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        return when(item.itemId){
            R.id.settigs -> {
                val settings = ListSiteFragmentDirections.actionListSiteFragmentToSettingsFragment()
                Navigation.findNavController((requireView())).navigate(settings)
                true
            }
            R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> {return true}

        }


    }

    companion object {
        private val TAG = ListSiteFragment::class.java.simpleName
        const val KEY_NAME = "name_extra_title"
        const val KEY_DESCRIPTION = "name_extra_description"
        const val KEY_IMAGE = "name_extra_imageUrl"
    }


}

