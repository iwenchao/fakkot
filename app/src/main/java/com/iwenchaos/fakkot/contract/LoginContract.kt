package com.iwenchaos.fakkot.contract

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.LoginResponse

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
        fun login(name: String, password: String, callback: OnRequstCallback<LoginResponse>)

        /**
         * 注册
         */
        fun register(name: String, password: String, repassword: String, callback: OnRequstCallback<LoginResponse>)


    }

    interface LoginView {
        /**
         * login success
         * @param result LoginResponse
         */
        fun loginSuccess(result: LoginResponse)

        /**
         * login failed
         * @param errorMessage error message
         */
        fun loginFailed(errorMessage: String?)

        /**
         * register success
         * @param result LoginResponse
         */
        fun registerSuccess(result: LoginResponse)

        /**
         * register failed
         * @param errorMessage error message
         */
        fun registerFailed(errorMessage: String?)

        /**
         * login or register success after operation
         * @param result LoginResponse
         */
        fun loginRegisterAfter(result: LoginResponse)
    }


    interface LoginPresenter {
        /**
         * 登陆
         */
        fun doLogin(name: String, password: String)

        /**
         *注册
         */
        fun doRegister(name: String, password: String, repassword: String)
    }

}