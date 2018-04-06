package com.iwenchaos.fakkot.ui.fragment

import android.os.Bundle
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.cnostant.Constant

/**
 * Created by chaos
 * on 2018/4/6. 11:11
 * 文件描述：
 */
class TypeArticleFragment : BaseFragment() {

    companion object {
        fun newInstance(id: Int): TypeArticleFragment {
            val fragment = TypeArticleFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, id)
            fragment.arguments = args
            return fragment
        }
    }


    override fun cancelRequest() {

    }
}