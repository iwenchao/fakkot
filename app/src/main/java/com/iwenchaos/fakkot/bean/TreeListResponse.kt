package com.iwenchaos.fakkot.bean

import java.io.Serializable

/**
 * Created by chaos
 * on 2018/3/24. 14:19
 * 文件描述：
 */
data class TreeListResponse(
        var errorCode: Int,
        var errorMsg: String?,
        var data: List<Data>
) {
    data class Data(
            var id: Int,
            var name: String,
            var courseId: Int,
            var parentChapterId: Int,
            var order: Int,
            var visible: Int,
            var children: List<Children>?
    ) : Serializable {
        data class Children(
                var id: Int,
                var name: String,
                var courseId: Int,
                var parentChapterId: Int,
                var order: Int,
                var visible: Int,
                var children: List<Children>?
        ) : Serializable
    }
}