package com.iwenchaos.fakkot.contract

/**
 * Created by chaos
 * on 2018/4/4. 09:20
 * 文件描述：
 */
interface LoginContract {


    interface LoginModel {
        /**
         * 登陆
         */
        fun login(name: String, password: String)
    }

    interface LoginView {
        fun start()
    }


    interface LoginPresenter {
        /**
         * 登陆
         */
        fun doLogin(name: String, password: String)
    }
}