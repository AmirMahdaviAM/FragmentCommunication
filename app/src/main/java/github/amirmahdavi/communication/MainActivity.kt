package github.amirmahdavi.communication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import github.amirmahdavi.communication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val sharedViewModel: SharedViewModel by viewModels()
    // Or you can use below line
    // private val sharedViewModel by lazy { ViewModelProvider(this).get(SharedViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationSetup()

        // UI Config
        binding.btnSetData.setOnClickListener {
            if (binding.etData.text.toString() == "") {
                sharedViewModel.setData("Text from Activity")
            } else {
                sharedViewModel.setData(binding.etData.text.toString())
            }
            binding.etData.setText("")
            binding.etData.clearFocus()
        }
        // Observe value from ViewModel and set in TextView
        sharedViewModel.getData.observe(this, Observer {
            binding.tvData.text = it.toString()
        })
    }


    // Navigation Stuff
    private fun navigationSetup() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_first, R.id.navigation_second)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }
}
