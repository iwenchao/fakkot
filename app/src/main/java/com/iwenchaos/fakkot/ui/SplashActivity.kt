package com.iwenchaos.fakkot.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity

/**
 * Created by chaos
 * on 2018/3/24. 13:53
 * 文件描述：
 */
class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({ startActivity(Intent(this@SplashActivity, MainActivity::class.java)) }, 3000)
        finish()
    }

    override fun setLayoutId(): Int = R.layout.activity_splash

    override fun cancelRequest() {}


}