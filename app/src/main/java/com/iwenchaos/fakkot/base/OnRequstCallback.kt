package com.iwenchaos.fakkot.base

/**
 * Created by chaos
 * on 2018/4/4. 11:30
 * 文件描述：
 */
interface OnRequstCallback<T : Any> {
    /**
     * 请求成功
     */
    fun success(t: T)

    /**
     * 请求失败
     */
    fun fail(errorMsg: String)

}