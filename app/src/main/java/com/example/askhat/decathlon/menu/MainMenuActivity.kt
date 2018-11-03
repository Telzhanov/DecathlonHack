package com.example.askhat.decathlon.menu

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.clubs.ClubsFragment
import com.example.askhat.decathlon.events.EventsFragment
import com.example.askhat.decathlon.store.StoreFragment
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var currentFragment:Fragment?=null
    var fragmentManager: FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)
        toolbar.title = "Мероприятий"
        var eventsFragment = EventsFragment()
        fragmentManager.beginTransaction().add(R.id.content,eventsFragment).commit()
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                toolbar.title = "Мероприятий"
                currentFragment = EventsFragment()
            }
            R.id.nav_store -> {
                toolbar.title = "Магазин"
                currentFragment = StoreFragment()
            }
            R.id.nav_clubs -> {
                toolbar.title = "Спортивные клубы"
                currentFragment = ClubsFragment()
            }
            R.id.nav_history -> {

            }
        }
        fragmentManager.beginTransaction().replace(R.id.content,currentFragment!!).commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
