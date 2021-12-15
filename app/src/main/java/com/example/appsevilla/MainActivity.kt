package com.example.appsevilla


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.appsevilla.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController)

   }
    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl('https://my-json-server.typicode.com/IDECOS/dbapi/')
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchBycoordinates(query:String){
        CoroutineScope(Dispatchers.IO).launch {
           val call:Response<SitePoi> = getRetrofit().create(APIService::class.java).getPoi('$query/id')
           val pois : SitePoi?= call.body()
           if (call.isSuccessful){
               // mostrar en recycleview
           } else {
               // mostrar error
           }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.settigs -> {
                val fragment = SettingsFragment()
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_list_site, fragment)
                    commit()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}