package com.iwenchaos.fakkot.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.gyf.barlibrary.ImmersionBar

/**
 * Created by chaos
 * on 2018/3/24. 13:36
 * 文件描述：
 */
abstract  class BaseActivity :AppCompatActivity() {

    protected lateinit var immersionBar : ImmersionBar

    private val imm : InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(setLayoutId())
        initImmersionBar()
    }

    open protected fun initImmersionBar(){
        //初始化 沉浸式bar
        immersionBar = ImmersionBar.with(this)
        immersionBar.init()
    }

    /**
     * layout id
     */
    protected abstract fun setLayoutId():Int

    /**
     * 取消该页面的request
     */
    protected abstract fun cancelRequest()


    override fun onDestroy() {
        super.onDestroy()
        //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        immersionBar.destroy()
    }

    override fun finish() {
        if (!isFinishing){
            super.finish()
            hideSoftKeyboard()
        }

    }

    private fun hideSoftKeyboard(){
        currentFocus?.let { imm.hideSoftInputFromInputMethod(it.windowToken,2) }
    }

}