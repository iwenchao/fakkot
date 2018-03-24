package com.iwenchaos.fakkot.bean

/**
 * Created by chaos
 * on 2018/3/24. 14:24
 * 文件描述：
 */
data class HotKeyResponse(
        var errorCode: Int,
        var errorMsg: String?,
        var data: List<Data>?
) {
    data class Data(
            var id: Int,
            var name: String,
            var link: Any,
            var visible: Int,
            var order: Int
    )
}