package com.example.a08_artur_leonel_pe

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class Activity_Configuracoes : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //declarando o drawerlayout
    private lateinit var drawerLayout: DrawerLayout
    private var notification = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isCharged ->
        if (isCharged) {
            showMessage("Notificação Ativada Com Sucesso!!")
        } else {
            showMessage("Notificação Recusada")
        }
    }

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
        layoutInflater.inflate(R.layout.activity_configuracoes, findViewById(R.id.frame_content))
        findViewById<Button>(R.id.button).setOnClickListener{
            AlertDialog.Builder(this).setMessage("Deseja Realmente Salvar?").setPositiveButton("Sim") { _, _ ->
                Toast.makeText(this, "Salvo Com Sucesso", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("Não") {_,_ ->
                null
            }.show()
        }

        findViewById<Switch>(R.id.notificacoes).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                    showMessage("Notificação Ativada!!")
                }
                else {
                    notification.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
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
                startActivity(Intent(this, Activity_Contato::class.java))
            }
            R.id.nav_Configurações -> {
                //Já está aqui
            }
            R.id.nav_WorldSkills -> {
                startActivity(Intent(this, Activity_WorldSkills::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showMessage (message:String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}