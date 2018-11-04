package com.example.askhat.decathlon.menu

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.auth.LoginActivity
import com.example.askhat.decathlon.auth.User
import com.example.askhat.decathlon.basket.BasketFragment
import com.example.askhat.decathlon.clubs.ClubsFragment
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.events.EventService
import com.example.askhat.decathlon.events.EventsFragment
import com.example.askhat.decathlon.history.HistoryFragment
import com.example.askhat.decathlon.store.StoreFragment
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.nav_header_main_menu.view.*
import org.koin.android.ext.android.inject

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var currentFragment:Fragment?=null
    var fragmentManager: FragmentManager = supportFragmentManager
    val eventService:EventService by inject()
    companion object {
        var user:User?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)
        var intent = intent
        user = intent.getSerializableExtra("user") as User
        toolbar.title = "Мероприятий"
        var eventsFragment = EventsFragment()
        var headerView = nav_view.getHeaderView(0)
        Logger.msg(user)
        headerView.textViewUserName.text = user?.name
        headerView.bonusTextView.text = user?.decopoint.toString()
        headerView.textViewUserEmail.text = user?.email
        fragmentManager.beginTransaction().add(R.id.content,eventsFragment).commit()
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

    }
    fun updatePoints(points:Int){
        var headerView = nav_view.getHeaderView(0)
        headerView.bonusTextView.text = points.toString()
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        var headerView = nav_view.getHeaderView(0)
        headerView.bonusTextView.text = user?.decopoint.toString()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                toolbar.title = "Мероприятий"
               currentFragment =  EventsFragment.newInstance(eventService)
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
                toolbar.title = "История"
                currentFragment = HistoryFragment()
            }
            R.id.nav_basket -> {
                toolbar.title = "Корзина"
                currentFragment = BasketFragment()
            }
        }
        fragmentManager.beginTransaction().replace(R.id.content,currentFragment!!).commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
