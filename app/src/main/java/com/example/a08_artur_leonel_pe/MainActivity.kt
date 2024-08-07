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
    //declarando o drawerlayout
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.base)

        //declarando os widgets/views
        drawerLayout = findViewById(R.id.drawer_layout)
        var navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener (this)

        //Inflar o activity main no frame_content do layout base
        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.frame_content))

        //Botão do menu caso seja acionado, ele abre o menu
        val menu = findViewById<ImageView>(R.id.menu).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    //Menu Em Ação
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> {

            }
            R.id.nav_contato -> {

            }
            R.id.nav_Configurações -> {

            }
            R.id.nav_WorldSkills -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}