package com.example.appsevilla.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsevilla.R
import com.example.appsevilla.adapter.SitiosAdapter
import com.example.appsevilla.model.SitePoi
import com.example.appsevilla.viewmodel.SiteViewModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class ListSiteFragment : Fragment() {

    private lateinit var listSites: ArrayList<SitePoi>
    private lateinit var siteAdapter: SitiosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var siteViewModel: SiteViewModel

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

        siteViewModel = ViewModelProvider(requireActivity()).get(SiteViewModel::class.java)

        recyclerView = view.findViewById(R.id.list_recycle)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        setupRecycleView()
        generateSites()

    }

    private fun setupRecycleView() {
        listSites = arrayListOf()
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

    private fun misitioOnClick(sitePoi: SitePoi, view: View) {
        Log.d(TAG, "Click en ${sitePoi.name}")
        siteViewModel.select(sitePoi)
        findNavController().navigate(R.id.action_listSiteFragment_to_detailFragment)

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun generateSites() {
        val sitioString = readSitesFromJsonFile()
        try {
            val sitiosJson = JSONArray(sitioString)
            for (i in 0 until sitiosJson.length()) {
                val sitioJson = sitiosJson.getJSONObject(i)
                val sitePoi = SitePoi(
                    sitioJson.optInt(id.toString()),
                    sitioJson.getString("name"),
                    sitioJson.getString("description"),
                    sitioJson.getString("imageUrl"),
                    sitioJson.getString("geo"),
                    sitioJson.getString("temperature"),
                    sitioJson.optInt("qualification")
                )
                Log.d(TAG, "generateSites: $sitePoi")
                listSites.add(sitePoi)
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater ) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

