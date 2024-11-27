package com.local.lift.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.local.locallift.R
import com.local.locallift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(navController.graph)

            if (!isUserLoggedIn()) {
                navController.navigate(R.id.signInFragment)
            }
        } else {
            Log.e("MainActivity", "NavHostFragment not found")
        }

        // Firebase test code
        testFirebaseConnection()
    }

    private fun isUserLoggedIn(): Boolean {
        return false
    }

    private fun testFirebaseConnection() {
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("Hello, Locallift!")
            .addOnSuccessListener {
                Log.d("MainActivity", "Data written successfully!")
            }
            .addOnFailureListener { exception ->
                Log.e("MainActivity", "Failed to write data", exception)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment
        val navController = navHostFragment?.navController
        return navController?.let { navigateUp(it, appBarConfiguration) } ?: super.onSupportNavigateUp()
    }
}
