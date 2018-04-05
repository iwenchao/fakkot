package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.contract.LoginContract
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/4/4. 09:24
 * 文件描述：
 */
class LoginModelImpl : LoginContract.LoginModel {


    /**
     * 登陆
     */
    override fun login(name: String, password: String) {
        async(UI) {
            tryCatch({
                it.printStackTrace()

            }){

            }
        }
    }

}