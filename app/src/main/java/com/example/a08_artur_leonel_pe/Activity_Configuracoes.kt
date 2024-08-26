package com.example.a08_artur_leonel_pe

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.WindowCompat

class Activity_Configuracoes : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private val notificationLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            showMessage("Notificação Ativada Com Sucesso!!")
        } else {
            showMessage("Notificação Recusada")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.base)

        // Configurando o DrawerLayout e o NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener(this)

        // Inflar o layout da Activity_Configuracoes no frame_content
        layoutInflater.inflate(R.layout.activity_configuracoes, findViewById(R.id.frame_content))

        // Configurar o botão de menu para abrir o DrawerLayout
        findViewById<ImageView>(R.id.menu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Configurar o botão de salvar com um AlertDialog
        findViewById<Button>(R.id.button).setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Deseja Realmente Salvar?")
                .setPositiveButton("Sim") { _, _ ->
                    Toast.makeText(this, "Salvo Com Sucesso", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não", null)
                .show()
        }

        // Configurar o Switch de notificações
        findViewById<Switch>(R.id.notificacoes).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                    showMessage("Notificação Ativada!!")
                } else {
                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }

        // Configurar o Switch de modo escuro
        findViewById<Switch>(R.id.darkmode).setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    // Lidar com seleção de itens do NavigationView
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> startActivity(Intent(this, MainActivity::class.java))
            R.id.nav_contato -> startActivity(Intent(this, Activity_Contato::class.java))
            R.id.nav_Configurações -> {
                // Já está aqui
            }
            R.id.nav_WorldSkills -> startActivity(Intent(this, Activity_WorldSkills::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Função para exibir mensagens Toast
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
