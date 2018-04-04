package com.iwenchaos.fakkot.contract

/**
 * Created by chaos
 * on 2018/4/4. 09:20
 * 文件描述：
 */
interface LoginContract {


    interface LoginModel {

        fun login()
    }

    interface LoginView {
        fun start()
    }


    interface LoginPresenter {

        fun doLogin(name: String, password: String)
    }
}