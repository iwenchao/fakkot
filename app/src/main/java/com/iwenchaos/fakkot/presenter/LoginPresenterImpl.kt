package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.LoginResponse
import com.iwenchaos.fakkot.contract.LoginContract
import com.iwenchaos.fakkot.model.LoginModelImpl

/**
 * Created by chaos
 * on 2018/4/4. 09:24
 * 文件描述：
 */
class LoginPresenterImpl constructor(val loginView: LoginContract.LoginView) : LoginContract.LoginPresenter {

    private val loginModel: LoginContract.LoginModel = LoginModelImpl()

    override fun doLogin(name: String, password: String) {
        loginModel.login(name, password, object : OnRequstCallback<LoginResponse> {

            override fun success(t: LoginResponse) {
                //登录成功

            }

            override fun fail(errorMsg: String) {
                //登录失败

            }

        })
    }
}