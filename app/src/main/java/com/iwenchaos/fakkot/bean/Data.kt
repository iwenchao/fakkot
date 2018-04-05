package com.iwenchaos.fakkot.bean

/**
 * Created by chaos
 * on 2018/3/24. 14:15
 * 文件描述：
 */
data class Data(
        var offSet: Int,
        var size: Int,
        var total: Int,
        var pageCount: Int,
        var curPage: Int,
        var over: Boolean,
        var datas: List<Datas>?
)