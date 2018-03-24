package com.iwenchaos.fakkot.bean

/**
 * Created by chaos
 * on 2018/3/24. 14:17
 * 文件描述：
 */
data class Datas (
        var id: Int,
        var originId: Int,
        var title: String,
        var chapterId: Int,
        var chapterName: String?,
        var envelopePic: Any,
        var link: String,
        var author: String,
        var origin: Any,
        var publishTime: Long,
        var zan: Any,
        var desc: Any,
        var visible: Int,
        var niceDate: String,
        var courseId: Int,
        var collect: Boolean
)