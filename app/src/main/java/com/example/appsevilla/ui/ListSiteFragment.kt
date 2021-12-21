package com.example.appsevilla.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsevilla.R
import com.example.appsevilla.adapter.SitiosAdapter
import com.example.appsevilla.model.SitePoi
import com.example.appsevilla.viewmodel.SiteViewModel

class ListSiteFragment : Fragment() {

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
        recyclerView = view.findViewById(R.id.list_recycle)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        setupRecycleView()
        setupViewModel()

    }

    private fun setupViewModel(){

        siteViewModel = ViewModelProvider(this).get(SiteViewModel::class.java)
        siteViewModel.getSiteObserver().observe(viewLifecycleOwner, {
            if (it != null){
                siteAdapter.setUpdateSite(it as ArrayList<SitePoi>)
            }else{
                Toast.makeText(activity, "Error de comunicacion", Toast.LENGTH_SHORT).show()
            }
        })
        siteViewModel.getApiPoi()
    }

    private fun setupRecycleView() {

        with(recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        siteAdapter = SitiosAdapter()
        siteAdapter.setOnItemClickListener(object : SitiosAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, list: SitePoi) {
                Log.d(TAG, "Click en ${list.name}")
                Toast.makeText(activity,"Has seleccionado ${list.name}",Toast.LENGTH_SHORT).show()
                val action = ListSiteFragmentDirections.actionListSiteFragmentToDetailFragment(
                    list.name,
                    list.description,
                    list.image,
                    list.geo,
                    list.temperature,
                    list.qualification,
                    list.id
                )
                Navigation.findNavController((requireView())).navigate(action)
            }
        })
        recyclerView.adapter = siteAdapter
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
    }

}

