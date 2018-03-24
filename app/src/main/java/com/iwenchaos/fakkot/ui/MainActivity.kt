package com.iwenchaos.fakkot.ui

import android.os.Bundle
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun setLayoutId(): Int = R.layout.activity_main


    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
