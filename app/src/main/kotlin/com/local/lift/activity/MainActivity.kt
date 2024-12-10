package com.local.lift.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.local.locallift.R
import com.local.locallift.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the NavHostFragment and check if it's available
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration(navController.graph)

            // First fragment loaded will be ActivityFragment, no login check here
            navController.navigate(R.id.activityFragment)
        } else {
            Log.e("MainActivity", "NavHostFragment not found")
        }

        testFirebaseConnection()
    }

    private fun testFirebaseConnection() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, Locallift! Not sure if it's working or not")
            .addOnSuccessListener {
                Log.d("MainActivity", "Data written successfully!")
            }
            .addOnFailureListener { exception ->
                Log.e("MainActivity", "Failed to write data", exception)
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment
        val navController = navHostFragment?.navController
        return navController?.let { NavigationUI.navigateUp(it, appBarConfiguration) } ?: super.onSupportNavigateUp()
    }
}
