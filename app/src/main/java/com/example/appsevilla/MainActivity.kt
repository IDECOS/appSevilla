package com.example.appsevilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList
import com.example.appsevilla.SitioSevilla as SitioSevilla

class MainActivity : AppCompatActivity() {

    private lateinit var listSites: ArrayList<SitioSevilla>
    private lateinit var siteAdapter: SitiosAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler)

        recycler = findViewById(R.id.list_site_sevilla)
        setupRecycleView()
        generateSites()
        //listSites =createMockSites()
    }

    private fun setupRecycleView(){
        listSites = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        siteAdapter = SitiosAdapter(listSites, this) { misitio ->
            misitioOnClick(misitio)
        }
        recycler.adapter = siteAdapter
    }

    private fun misitioOnClick(misitio: SitioSevilla){
        Log.d(TAG, "Click en ${misitio.nameSite}")
        val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(KEY_NAME, misitio.nameSite)
            intent.putExtra(KEY_DESCRIPTION, misitio.description)
            intent.putExtra(KEY_IMAGE, misitio.imageUrl)
        startActivity(intent)
        //navegateToDetail(misitio)

    }

   /* private fun navegateToDetail(misitio: SitioSevilla){
        val intent = Intent(this, DetailActivity::class.java).apply {
            intent.putExtra(KEY_NAME, misitio.nameSite)
            intent.putExtra(KEY_DESCRIPTION, misitio.description)
            intent.putExtra(KEY_IMAGE, misitio.imageUrl)

        }
        startActivity(intent)
    }*/

    private fun generateSites(){
        val sitioString = readSitesFromJsonFile()
        try {
            val sitiosJson = JSONArray(sitioString)
            for (i in 0 until sitiosJson.length()){
                val sitioJson = sitiosJson.getJSONObject(i)
                val sitioSevilla = SitioSevilla(
                    sitioJson.getString("name_site"),
                    sitioJson.getString("description"),
                    sitioJson.getString("rate"),
                    sitioJson.getString("imageUrl")
                )
                Log.d(TAG, "generateSites: $sitioSevilla")
                listSites.add(sitioSevilla)
            }
            siteAdapter.notifyDataSetChanged()
        }catch (e: JSONException){
            e.printStackTrace()
        }
    }

    private fun readSitesFromJsonFile(): String? {
        var sitesSevillaString: String? = null
        try {
            val inputStream = assets.open("site_sevilla.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            sitesSevillaString = String(buffer)
        }catch (e: IOException){
            e.printStackTrace()
        }
        return sitesSevillaString
    }

    /*private fun createMockContacts(): ArrayList<Contact> {
        return arrayListOf(
            Contact("Jose", "Perez", "jose@gmail.com", ""),
            Contact("Jose", "Perez", "jose@gmail.com", ""),
            Contact("Jose", "Perez", "jose@gmail.com", ""),
            Contact("Juan", "Perez", "juan@gmail.com", "")
        )
    }*/

    companion object{
        private val TAG = MainActivity::class.java.simpleName
        const val KEY_NAME = "name_extra_title"
        const val KEY_DESCRIPTION = "name_extra_description"
        const val KEY_IMAGE = "name_extra_imageUrl"
    }
}