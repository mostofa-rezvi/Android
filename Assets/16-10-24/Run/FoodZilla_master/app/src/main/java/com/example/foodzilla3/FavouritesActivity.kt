package com.example.foodzilla3

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.DB.DBHelper
import com.example.foodzilla3.adaptor.FavouritesListAdaptor
import com.example.foodzilla3.login.LoginActivity
import com.example.foodzilla3.model.restaurant.MenuType
import com.example.foodzilla3.model.restaurant.Resturant
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class FavouritesActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouritesListAdaptor
    private val favoriteRestaurants: ArrayList<Resturant> = ArrayList()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        auth= FirebaseAuth.getInstance()
        drawerLayout = findViewById(R.id.drawerLayoutFav)
        navigationView = findViewById(R.id.navigationViewFav)
        val menu: Menu = navigationView.menu
        val menuItem: MenuItem = menu.findItem(R.id.menu_favourates)
        menuItem.isChecked = true

        val toolbar: Toolbar = findViewById(R.id.toolbarFavourites)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        setupNavigationDrawer()


        dbHelper = DBHelper(this)
        recyclerView=findViewById(R.id.rvRestaurantInFav)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= FavouritesListAdaptor(favoriteRestaurants)
        recyclerView.adapter=adapter
        loadFavoriteRestaurants()

    }
    private fun loadFavoriteRestaurants() {
        val db: SQLiteDatabase = dbHelper.readableDatabase

        val projection = arrayOf(DBHelper.COLUMN_NAME, DBHelper.COLUMN_RATING)
        val sortOrder = "${DBHelper.COLUMN_NAME} ASC"

        val cursor: Cursor = db.query(
            DBHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        favoriteRestaurants.clear()

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
            val rating = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_RATING))
            val restaurant = Resturant(arrayListOf<MenuType>(),name, rating)
            favoriteRestaurants.add(restaurant)
        }

        cursor.close()
        db.close()

        adapter.notifyDataSetChanged()
    }
    private fun setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_favourates -> {
                    // Handle favourites item click
                    startActivity(Intent(this, FavouritesActivity::class.java))
                    showToast("Favourites clicked")
                }

                R.id.menu_restaurants -> {
                    // Handle profile item click
                    showToast("Profile clicked")
                    startActivity(Intent(this,MainActivity::class.java))
                }

                R.id.menu_logout -> {
                    // Handle favourite restaurants item click
                    showToast("Logout")

                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }

            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}