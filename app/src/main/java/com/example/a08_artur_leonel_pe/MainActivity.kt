package com.example.a08_artur_leonel_pe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.base)

        drawerLayout = findViewById(R.id.drawer_layout)
        var navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener (this)

        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.frame_content))

        val menu = findViewById<ImageView>(R.id.menu).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_contato -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}