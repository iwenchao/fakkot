package com.iwenchaos.fakkot.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.bean.BannerResponse

/**
 * Created by chaos
 * on 2018/3/24. 17:34
 * 文件描述：
 */
class BannerAdapter(val context: Context, datas: MutableList<BannerResponse.Data>)
    : BaseQuickAdapter<BannerResponse.Data, BaseViewHolder>(R.layout.banner_item, datas) {
    override fun convert(helper: BaseViewHolder?, item: BannerResponse.Data?) {

    }
}