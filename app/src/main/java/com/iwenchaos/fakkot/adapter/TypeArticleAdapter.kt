package com.iwenchaos.fakkot.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.bean.Datas

/**
 * Created by chaos
 * on 2018/4/6. 18:32
 * 文件描述：
 */
class TypeArticleAdapter(val context:Context,datas:MutableList<Datas>) :BaseQuickAdapter<Datas,BaseViewHolder>(R.layout.home_list_item,datas) {

    override fun convert(helper: BaseViewHolder, item: Datas?) {
        item ?: return
        @Suppress("DEPRECATION")
        helper.setText(R.id.homeItemTitle, item.title)
                .setText(R.id.homeItemAuthor, item.author)
                .setVisible(R.id.homeItemType, false)
                .setText(R.id.homeItemDate, item.niceDate)
                .setImageResource(
                        R.id.homeItemLike,
                        if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like
                )
                .addOnClickListener(R.id.homeItemLike)

    }
}