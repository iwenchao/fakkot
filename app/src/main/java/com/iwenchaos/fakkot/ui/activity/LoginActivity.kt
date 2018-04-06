package com.iwenchaos.fakkot.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.iwenchaos.fakkot.base.Preference
import com.iwenchaos.fakkot.bean.LoginResponse
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.LoginContract
import com.iwenchaos.fakkot.presenter.LoginPresenterImpl
import com.iwenchaos.fakkot.toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by chaos
 * on 2018/3/24. 18:06
 * 文件描述：
 */
class LoginActivity : BaseActivity(), LoginContract.LoginView, View.OnClickListener {

    //by Preference的用法？
    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    private var user: String by Preference(Constant.USERNAME_KEY, "")
    private var psd: String by Preference(Constant.PASSWORD_KEY, "")

    private val loginPresenter: LoginContract.LoginPresenter by lazy {
        LoginPresenterImpl(this)
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
            when (v.id) {
                R.id.login -> {
                    if (valid()) {
                        loginProgress.visibility = View.VISIBLE
                        loginPresenter.doLogin(username.text.toString(), password.text.toString())
                    }
                }
                R.id.register -> {
                    if (valid()) {
                        loginProgress.visibility = View.VISIBLE
                        loginPresenter.doRegister(
                                username.text.toString(),
                                password.text.toString(),
                                password.text.toString()
                        )
                    }
                }
                R.id.loginExit -> {
                    finish()
                }
            }
        }
    }

    /**
     * 检查输入是否合法
     */
    private fun valid(): Boolean {
        username.error = null
        password.error = null

        var cancel = false
        var focusView: View? = null

        val usernameTxt = username.text
        val passwordTxt = password.text

        //检查昵称是否合法
        if (TextUtils.isEmpty(usernameTxt)) {
            toast(R.string.username_not_empty)
            cancel = true
            focusView = username
        }
        //检查密码是否合法
        if (TextUtils.isEmpty(passwordTxt)) {
            toast(R.string.password_confirm_not_empty)
            cancel = true
            focusView = password
        } else if (passwordTxt.length < 6) {
            toast(R.string.password_length_short)
            cancel = true
            focusView = password
        }


        if (cancel) {
            focusView?.requestFocus()
            return false
        } else {
            return true
        }

    }

    override fun loginSuccess(result: LoginResponse) {
        toast(R.string.login_success)
    }

    override fun loginFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        errorMessage?.let {
            toast(it)
        }

    }

    override fun loginRegisterAfter(result: LoginResponse) {
        isLogin = true
        user = result.data.username
        psd = result.data.password
        loginProgress.visibility = View.GONE
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(Constant.CONTENT_TITLE_KEY, result.data.username)
            finish()
        })


    }

    override fun registerSuccess(result: LoginResponse) {
        toast(R.string.register_success)

    }

    override fun registerFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        username.requestFocus()
        errorMessage?.let {
            toast(it)
        }

    }

    override fun cancelRequest() {
    }
}