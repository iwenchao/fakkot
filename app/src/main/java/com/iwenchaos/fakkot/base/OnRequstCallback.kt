package com.iwenchaos.fakkot.base

/**
 * Created by chaos
 * on 2018/4/4. 11:30
 * 文件描述：
 */
interface OnRequstCallback<T : Any> {

    fun success(t: T)

    fun fail(errorMsg: String)

    fun before()
}