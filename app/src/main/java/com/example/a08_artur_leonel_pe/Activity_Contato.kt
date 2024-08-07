package com.example.a08_artur_leonel_pe

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView
import java.nio.channels.InterruptedByTimeoutException

class Activity_Contato : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
        layoutInflater.inflate(R.layout.activity_contato, findViewById(R.id.frame_content))

        //Lógica para levar para uma pagina web
        findViewById<Button>(R.id.rota_Button).setOnClickListener{
            val link = Uri.parse("https://maps.app.goo.gl/ZZC3Z7fWbzxRMugr8")
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)
        }

        //Botão do menu caso seja acionado, ele abre o menu
        val menu = findViewById<ImageView>(R.id.menu).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    //Menu Em Ação
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_contato -> {
                //Já Está Aqui
            }
            R.id.nav_Configurações -> {
                startActivity(Intent(this, Activity_Configuracoes::class.java))
            }
            R.id.nav_WorldSkills -> {
                startActivity(Intent(this, Activity_WorldSkills::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}