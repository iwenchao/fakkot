package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.LoginResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.LoginContract
import com.iwenchaos.fakkot.net.RetrofitHelper
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/4/4. 09:24
 * 文件描述：
 */
class LoginModelImpl : LoginContract.LoginModel {
    private var loginAsync: Deferred<LoginResponse>? = null


    /**
     * 注册
     */
    override fun register(name: String, password: String, repassword: String, callback: OnRequstCallback<LoginResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }) {
                loginAsync?.cancelByActive()
                loginAsync = RetrofitHelper.retrofistService.doRegister(name, password, repassword)
                val result = loginAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }
        }

    }

    /**
     * 登陆
     */
    override fun login(name: String, password: String, callback: OnRequstCallback<LoginResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }) {
                loginAsync?.cancelByActive()
                loginAsync = RetrofitHelper.retrofistService.doLogin(name, password)
                val result = loginAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }
        }
    }

}