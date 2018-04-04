package com.iwenchaos.fakkot.ui.activity

import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.contract.LoginContract

/**
 * Created by chaos
 * on 2018/3/24. 18:06
 * 文件描述：
 */
class LoginActivity : BaseActivity(), LoginContract.LoginView {


    override fun start() {

    }


    override fun setLayoutId(): Int = R.layout.activity_splash

    override fun cancelRequest() {
    }
}