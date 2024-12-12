import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import com.local.locallift.R
import com.local.locallift.databinding.FragmentLandingBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: FragmentLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "MainActivity created and layout set")

        setupNavigation()

        testFirebaseConnection()
    }

    private fun setupNavigation() {
        Log.d("MainActivity", "Setting up navigation")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as? NavHostFragment
        navHostFragment?.let { host ->
            val navController = host.navController
            appBarConfiguration = AppBarConfiguration(navController.graph)
            Log.d("MainActivity", "NavHostFragment found and NavController set")
        } ?: run {
            Log.e("MainActivity", "NavHostFragment not found!")
        }
    }

    private fun testFirebaseConnection() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, LocalLift!")
            .addOnSuccessListener {
                Log.d("MainActivity", "Data written successfully!")
            }
            .addOnFailureListener { exception ->
                Log.e("MainActivity", "Failed to write data", exception)
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("MainActivity", "Navigating Up")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as? NavHostFragment
        val navController = navHostFragment?.navController
        return navController?.let { navigateUp(it, appBarConfiguration) } ?: super.onSupportNavigateUp()
    }
}
