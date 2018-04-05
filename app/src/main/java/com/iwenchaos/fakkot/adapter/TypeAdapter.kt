package com.iwenchaos.fakkot.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.bean.TreeListResponse

/**
 * Created by chaos
 * on 2018/4/5. 19:58
 * 文件描述：
 */
class TypeAdapter(val context: Context,
                  datas: MutableList<TreeListResponse.Data>)
    : BaseQuickAdapter<TreeListResponse.Data, BaseViewHolder>(R.layout.type_list_item, datas) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder, item: TreeListResponse.Data) {
        helper.setText(R.id.typeItemFirst, item.name)
        item.children?.let { children ->
            helper.setText(
                    R.id.typeItemSecond,
                    children.joinToString("     ", transform = { child ->
                        child.name
                    })
            )
        }
    }
}