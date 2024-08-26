package com.example.a08_artur_leonel_pe

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Activity_Contato : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener(this)

        // Inflate the activity_contato layout into the frame_content
        layoutInflater.inflate(R.layout.activity_contato, findViewById(R.id.frame_content))

        val nome = findViewById<EditText>(R.id.nome)
        val email = findViewById<EditText>(R.id.email)
        val mensagem = findViewById<EditText>(R.id.mensagem)

        // Setup the menu button to open the drawer
        findViewById<ImageView>(R.id.menu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        findViewById<Button>(R.id.rota_Button).setOnClickListener {
            val link = Uri.parse("https://maps.app.goo.gl/ZZC3Z7fWbzxRMugr8")
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val inputNome = nome.text.toString()
            val inputEmail = email.text.toString()
            val inputMensagem = mensagem.text.toString()

            if (inputMensagem.isBlank() || inputEmail.isBlank() || inputNome.isBlank()) {
                Toast.makeText(this, "Campos Inválidos!! \uD83E\uDD14", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Dados recebidos com sucesso!! \uD83D\uDE0A", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_contato -> {
                // Já Está Aqui
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
