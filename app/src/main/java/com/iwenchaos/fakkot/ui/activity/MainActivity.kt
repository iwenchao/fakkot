package com.iwenchaos.fakkot.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.AppCompatButton
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.toast
import com.iwenchaos.fakkot.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var lastTime: Long = 0
    private var currentIndex = 0
    private var homeFragment: HomeFragment? = null
    private var typeFragment: HomeFragment? = null
    private var commonUseFragment: HomeFragment? = null


    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    private val username: String by Preference(Constant.USERNAME_KEY, "")

    private lateinit var navigationViewUsername: TextView
    private lateinit var navigationViewLogout: AppCompatButton


    override fun setLayoutId(): Int = R.layout.activity_main

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        //底部导航按钮
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_home
        }
        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }
        navigationView.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }

        navigationViewUsername = navigationView.getHeaderView(0).findViewById(R.id.navigationViewUsername)
        navigationViewLogout = navigationView.getHeaderView(0).findViewById(R.id.navigationViewLogout)
        navigationViewUsername.run {
            if (!isLogin) {
                text = getString(R.string.not_login)
            } else {
                text = username
            }
        }

        navigationViewLogout.run {
            text = if (!isLogin) {
                getString(R.string.goto_login)
            } else {
                getString(R.string.logout)
            }
            setOnClickListener {
                if (!isLogin) {
                    Intent(this@MainActivity, LoginActivity::class.java).run {
                        startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                    }
                } else {
                    Preference.clear()
                    navigationViewUsername.text = getString(R.string.not_login)
                    text = getString(R.string.goto_login)
                    homeFragment?.refreshData()
                }
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == R.id.menuSearch) {
                Intent(this, SearchActivity::class.java).run {
                    startActivity(this)
                }
                return true
            }
            when (item.itemId) {
                R.id.menuSearch -> {
                    if (currentIndex == R.id.menuHot) {

                    }
                }
                R.id.menuHot -> {
                    if (currentIndex == R.id.menuHot) {
                        commonUseFragment?.refreshData()
                    }
                    setFragment(R.id.menuHot)
                    currentIndex = R.id.menuHot
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        if (isLogin && navigationViewUsername.text.toString() != username) {
            navigationViewUsername.text = username
            navigationViewLogout.text = getString(R.string.logout)
            homeFragment?.refreshData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constant.MAIN_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    navigationViewUsername.text = data?.getStringExtra(Constant.CONTENT_TITLE_KEY)
                    navigationViewLogout.text = getString(R.string.logout)
                }
                homeFragment?.refreshData()
            }
            Constant.MAIN_LIKE_REQUEST_CODE -> {
                homeFragment?.refreshData()
            }
        }
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        val curTime = System.currentTimeMillis()
        if (curTime - lastTime < 2 * 1000) {
            super.onBackPressed()
            finish()
        } else {
            toast(R.string.double_click_exit)
            lastTime = curTime
        }


        super.onBackPressed()
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val onDrawerNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_like -> {
                if (!isLogin) {
                    Intent(this, LoginActivity::class.java).run {
                        startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                    }
                    toast(getString(R.string.login_please_login))
                    return@OnNavigationItemSelectedListener true //TODO 这里不懂为什么要true
                }
                Intent(this, SearchActivity::class.java).run {
                    putExtra(Constant.SEARCH_KEY, false)
                    startActivityForResult(this, Constant.MAIN_LIKE_REQUEST_CODE)
                }
            }
            R.id.nav_about -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        true
    }


    //底部导航按钮的监听器
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        setFragment(item.itemId)
        return@OnNavigationItemSelectedListener when (item.itemId) {
            R.id.navigation_home -> {
                if (currentIndex == R.id.navigation_home) {
                    homeFragment?.smoothScrollToPosition()
                }
                currentIndex = R.id.navigation_home
                true
            }
            R.id.navigation_type -> {
                if (currentIndex == R.id.navigation_home) {
                    homeFragment?.smoothScrollToPosition()
                }
                currentIndex = R.id.navigation_home
                true
            }
            else -> {
                false
            }
        }
    }


    private fun setFragment(index: Int) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        fragmentManager.beginTransaction().apply {
            //首页
            homeFragment ?: let {
                HomeFragment().let {
                    homeFragment = it
                    add(R.id.content, it)
                }
            }
            //类型
            homeFragment ?: let {
                HomeFragment().let {
                    homeFragment = it
                    add(R.id.content, it)
                }
            }
            //常用
            homeFragment ?: let {
                HomeFragment().let {
                    homeFragment = it
                    add(R.id.content, it)
                }
            }

            hideFragment(this)
            when (index) {
                R.id.navigation_home -> {
                    toolbar.title = getString(R.string.app_name)
                    homeFragment?.let {
                        this.show(it)
                    }
                }
                R.id.navigation_type -> {
                    toolbar.title = getString(R.string.title_dashboard)
                    typeFragment?.let {
                        this.show(it)
                    }
                }
                R.id.menuHot -> {
                    toolbar.title = getString(R.string.hot_title)
                    commonUseFragment?.let {
                        this.show(it)
                    }
                }
            }
        }.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        homeFragment?.let {
            transaction.hide(it)
        }

        typeFragment?.let {
            transaction.hide(it)
        }
        commonUseFragment?.let {
            transaction.hide(it)
        }
    }

}
