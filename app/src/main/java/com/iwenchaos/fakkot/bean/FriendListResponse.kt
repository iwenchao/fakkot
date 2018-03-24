package com.iwenchaos.fakkot.bean

/**
 * Created by chaos
 * on 2018/3/24. 14:24
 * 文件描述：
 */
data class FriendListResponse(
        var errorCode: Int,
        var errorMsg: String?,
        var data: List<Data>?
) {
    data class Data(
            var id: Int,
            var name: String,
            var link: String,
            var visible: Int,
            var order: Int,
            var icon: Any
    )
}