package com.example.a08_artur_leonel_pe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //declarando o drawerlayout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var carousel: ViewPager
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.base)

        //declarando os widgets/views
        drawerLayout = findViewById(R.id.drawer_layout)
        var navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener(this)

        //Inflar o activity main no frame_content do layout base
        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.frame_content))

        val textBemVindo = findViewById<TextView>(R.id.text_bem_vindo)
        val worldSkills = findViewById<Button>(R.id.worldSkills)
        val contato = findViewById<Button>(R.id.Contato)
        val menuInflar = findViewById<Button>(R.id.Menu)


        val userName = intent.getStringExtra("username")
        textBemVindo.text = "Olá $userName!!"

        setupCarousel()
        startAutoScroll()

        worldSkills.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.worldskills, layoutInterno, true)
        }

        contato.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.contato, layoutInterno, true)
        }

        menuInflar.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.home, layoutInterno, true)
        }

        //Botão do menu caso seja acionado, ele abre o menu
        val menu = findViewById<ImageView>(R.id.menu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setupCarousel() {
        carousel = findViewById(R.id.carousel)
        val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        carousel.adapter = CarouselAdapter(images)
    }

    // Inicia a rotação automática do carrossel
    private fun startAutoScroll() {
        val update = Runnable {
            if (currentPage == (carousel.adapter?.count ?: 1) - 1) {
                currentPage = 0
            } else {
                currentPage++
            }
            carousel.setCurrentItem(currentPage, true)
        }

        handler.postDelayed(object : Runnable {
            override fun run() {
                update.run()
                handler.postDelayed(this, 3000)
            }
        }, 3000)
    }

    //Menu Em Ação
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> {
                //Já está aqui
            }

            R.id.nav_contato -> {
                startActivity(Intent(this, Activity_Contato::class.java))
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