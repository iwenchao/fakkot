package com.iwenchaos.fakkot.cnostant

import android.widget.Toast

/**
 * Created by chaos
 * on 2018/3/24. 12:46
 * 文件描述：
 */
object Constant {

    /**
     * share preference name
     */
    const val SHARED_NAME = "_preference"
    /**
     * Toast
     */
    @JvmField
    var showToast:Toast? = null

    /**
     * id key
     */
    const val CONTENT_ID_KEY = "id"

    /**
     * url key
     */
    const val CONTENT_URL_KEY = "url"
    /**
     * title key
     */
    const val CONTENT_TITLE_KEY = "title"
    /**
     * target key
     */
    const val CONTENT_TARGET_KEY = "target"
    /**
     * cid key
     */
    const val CONTENT_CID_KEY = "cid"



    const val MAIN_REQUEST_CODE = 100
    const val MAIN_LIKE_REQUEST_CODE = 101
    const val LOGIN_KEY="LOGIN_KEY"

}