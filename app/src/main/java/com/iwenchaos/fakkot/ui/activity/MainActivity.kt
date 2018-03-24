package com.iwenchaos.fakkot.ui.activity

import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity

class MainActivity : BaseActivity() {

    private var lastTime : Long = 0
    private var currentIndex = 0
//    private var homeFragment



    override fun setLayoutId(): Int = R.layout.activity_main


    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
