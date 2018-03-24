package com.iwenchaos.fakkot.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.bean.Datas

/**
 * Created by chaos
 * on 2018/3/24. 17:18
 * 文件描述：
 */
class HomeAdapter(val context: Context, datas: MutableList<Datas>)
    : BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.home_list_item, datas) {
    override fun convert(helper: BaseViewHolder?, item: Datas?) {
        item ?: return
        //这里可以把数据bind view


    }

}