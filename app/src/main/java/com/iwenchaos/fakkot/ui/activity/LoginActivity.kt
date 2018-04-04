package com.iwenchaos.fakkot.ui.activity

import android.os.Bundle
import android.view.View
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by chaos
 * on 2018/3/24. 18:06
 * 文件描述：
 */
class LoginActivity : BaseActivity(), LoginContract.LoginView, View.OnClickListener {


    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)


    override fun start() {

    }

    override fun setLayoutId(): Int = R.layout.activity_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
        loginExit.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.login -> {

                }
                R.id.register -> {

                }

            }
        }
    }

    override fun cancelRequest() {
    }
}