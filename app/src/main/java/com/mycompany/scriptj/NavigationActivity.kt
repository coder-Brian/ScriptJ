package com.mycompany.scriptj

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mycompany.scriptj.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    // Variables to handle navigation and Firebase authentication
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the layout using View Binding
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the toolbar as the action bar for the activity
        setSupportActionBar(binding.appBarNavigation.toolbar)

        // Set up a click listener for the Floating Action Button (FAB)
        binding.appBarNavigation.fab.setOnClickListener { view ->
            // Show a Snackbar when the FAB is clicked (for demonstration purposes)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Initialize navigation components, such as the DrawerLayout and NavigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)

        // Specify the top-level destinations and the DrawerLayout for the app bar configuration
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        // Set up the ActionBar with the navigation controller and app bar configuration
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Connect the NavigationView to the navigation controller
        navView.setupWithNavController(navController)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    // Handle item selection in the options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logOut -> {
                // Handle LOG OUT button click
                signOut()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Function to sign out the user and navigate to the login screen
    private fun signOut() {
        // Sign out the user using Firebase Authentication
        auth.signOut()

        // Clear the back stack and redirect to the login screen
        val intent = Intent(this, LogInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    // Navigate up to the parent activity in the action bar.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
