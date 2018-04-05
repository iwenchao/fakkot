package com.iwenchaos.fakkot.bean

/**
 * Created by chaos
 * on 2018/3/24. 14:22
 * 文件描述：
 */
data class LoginResponse(
        var errorCode: Int,
        var errorMsg: Int,
        var data: Data
) {
    data class Data(
            var id: Int,
            var username: String,
            var password: String,
            var icon: String?,
            var type: Int,
            var collectIds: List<Int>?
    )
}