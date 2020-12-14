package com.supinfo.andm.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.supinfo.andm.R
import com.supinfo.andm.ui.about.AboutActivity
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var homeViewModel: HomeViewModel
    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val disc = findViewById<Button>(R.id.discover)
        discover.setOnClickListener { v ->  homeViewModel.discoverClicked() }
        search.setOnClickListener { v ->
            val text = cocktail_input.text
            homeViewModel.searchClicked(text.toString()) }

        val navView = findViewById<NavigationView>(R.id.nav_view)

        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        initViewModel()

    }
    /**
     * Initialize the viewModel
     */
    private fun initViewModel() {
        val provider: HomeViewModel by viewModels()
        homeViewModel = provider
        homeViewModel.welcome.observe(this, Observer { welcome_TV.text = it })

        this.applicationContext?.let { GlideApp.with(it).load(R.drawable.giff).into(
            backgroundImageView
        ) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.action_about) {
            AboutActivity.start(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.action_about -> AboutActivity.start(this)
        }
        return true
    }
}