package com.example.foodzilla3

import ImagePicker
import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.adaptor.ResturantListAdaptor
import com.example.foodzilla3.login.LoginActivity
import com.example.foodzilla3.model.restaurant.Item
import com.example.foodzilla3.model.restaurant.MenuType
import com.example.foodzilla3.model.restaurant.Resturant
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.foodzilla3.adaptor.ResturantListAdaptor.OnItemClickListener as OnItemClickListener1

object SelectedRestaurant {
    var restaurant: Resturant? = null
}

class MainActivity : OnItemClickListener1, AppCompatActivity() {
    var listOfAllResturant = arrayListOf<Resturant>()
    lateinit var rvResturant: RecyclerView
    lateinit var progBar: ProgressBar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    lateinit var auth: FirebaseAuth
   lateinit var cam:ImagePicker
lateinit var imgProfile:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progBar = findViewById(R.id.pBarRestaurantLoading)
        navigationView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        val menu: Menu = navigationView.menu
        val menuItem: MenuItem = menu.findItem(R.id.menu_restaurants)
        menuItem.isChecked = true
       cam=ImagePicker(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        progBar.visibility = View.VISIBLE
        supportActionBar?.title = "Resturants"
        auth = FirebaseAuth.getInstance()


        rvResturant = findViewById(R.id.rvRestaurant)
        var firebaseDB = FirebaseDatabase.getInstance()
        val ref = firebaseDB.reference.child("restaurants")

        val adaptor = ResturantListAdaptor(listOfAllResturant, this)
        rvResturant.adapter = adaptor
        rvResturant.layoutManager = LinearLayoutManager(this)
        setupNavigationDrawer()
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (resturantSnapshot in snapshot.children) {
                        var resturant = Resturant(arrayListOf())
                        resturant.resturantName =
                            resturantSnapshot.child("name").getValue(String::class.java).toString()
                        resturant.resRating =
                            resturantSnapshot.child("restaurantRating").getValue(String::class.java)
                                .toString()

                        val menuSnapshot = resturantSnapshot.child("menu")
                        for (menuSnap in menuSnapshot.children) {
                            var menuType = menuSnap.key?.let { MenuType(it, arrayListOf()) }
                            val itemSnapshot = menuSnap.children

                            for (itemSnap in itemSnapshot) {
                                var item = Item()

                                item.name =
                                    itemSnap.child("name").getValue(String::class.java).toString()
                                item.price =
                                    itemSnap.child("price").getValue(Int::class.java).toString()
                                item.rating =
                                    itemSnap.child("rating").getValue(Int::class.java).toString()
                                if (menuType != null) {
                                    menuType.addItem(item)
                                }
                            }
                            if (menuType != null) {
                                resturant.addMenu(menuType)
                            }
                        }
                        resturant.displayRes()
                        listOfAllResturant.add(resturant)
                        adaptor.notifyDataSetChanged()
                        if (progBar.isVisible) {
                            progBar.visibility = View.INVISIBLE
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        for (res in listOfAllResturant) {
            res.displayRes()
        }
//            Log.d("Database output", listOfAllResturant[0].resturantName)

    }

    override fun onItemClick(restaurant: Resturant) {
        Intent(this@MainActivity, MenuActivity::class.java).apply {
            SelectedRestaurant.restaurant = restaurant
            startActivity(this)
        }
    }

    private fun setupNavigationDrawer() {
        var headerview = navigationView.getHeaderView(0)
        var userName = headerview.findViewById<TextView>(R.id.txtNavHeader)
        var userEmail = headerview.findViewById<TextView>(R.id.txtNavHeaderEmail)
        var btnChangePic=headerview.findViewById<ImageButton>(R.id.btnAddPic)
        imgProfile=headerview.findViewById<ImageView>(R.id.imgProfileImage)
        btnChangePic.setOnClickListener {
            cam.showPickerDialog()

        }


        var userCollection = Firebase.firestore.collection("Users")
        var auth = FirebaseAuth.getInstance()
        userCollection.document("${auth.currentUser?.uid.toString()}").get().addOnSuccessListener {
            if (it.exists()) {
                userName.text ="Name : "+ it.getString("name")
                userEmail.text = it.getString("email")
            }
            else{
                Toast.makeText(this, "Didn't find user", Toast.LENGTH_LONG).show()

            }
        }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }

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
                }

                R.id.menu_logout -> {
                    // Handle favourite restaurants item click
                    showToast("Logout")
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.munu_settings->{
                    startActivity(Intent(this,SettingActivity::class.java))

                }

            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Call handleActivityResult() and pass the ImageView instance
        cam.handleActivityResult(requestCode, resultCode, data,imgProfile)
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

