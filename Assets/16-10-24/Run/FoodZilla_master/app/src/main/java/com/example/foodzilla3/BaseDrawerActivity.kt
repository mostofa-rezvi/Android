package com.example.foodzilla3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

abstract class BaseDrawerActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_drawer)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(toolbar)

        // Set up the navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView= findViewById(R.id.navigationView)

        // Set up the ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set up navigation item selected listener
        navigationView.setNavigationItemSelectedListener(this)

        // Set the initial fragment
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                showToast("Navigation Item 1 Clicked")
                // Handle navigation item 1 click
            }
            R.id.menu_restaurants -> {
                showToast("Navigation Item 2 Clicked")
                // Handle navigation item 2 click
            }
            R.id.menu_favourates->{
                showToast(("CLICKED FAV"))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
        }

    abstract fun getLayoutResourceId(): Int

    abstract fun getToolbar(): androidx.appcompat.widget.Toolbar

    // Helper function to replace the main content with a fragment
//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.mainContent, fragment)
//            .commit()
//    }

    // Handle back button press to close the navigation drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
        fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
}
