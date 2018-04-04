package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.contract.LoginContract

/**
 * Created by chaos
 * on 2018/4/4. 09:24
 * 文件描述：
 */
class LoginPresenterImpl constructor(val loginView: LoginContract.LoginView) : LoginContract.LoginPresenter {


    override fun doLogin(name: String, password: String) {

    }
}