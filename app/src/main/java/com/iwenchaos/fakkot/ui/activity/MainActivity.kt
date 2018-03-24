package com.iwenchaos.fakkot.ui.activity

import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {

    private var lastTime : Long = 0
    private var currentIndex = 0
    private var homeFragment :HomeFragment? = null



    override fun setLayoutId(): Int = R.layout.activity_main


    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
