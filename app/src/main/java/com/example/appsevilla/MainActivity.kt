package com.example.appsevilla


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController)

   }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        return when(item.itemId){
            R.id.settigs -> {
                val settings = SettingsFragment()
                ft.add(R.id.fragment_list_site, settings).commit()
                ft.addToBackStack(null)
                true
            }
            R.id.home -> {
                onBackPressed()
                true
            }
            else -> {return true}

        }
    }
}