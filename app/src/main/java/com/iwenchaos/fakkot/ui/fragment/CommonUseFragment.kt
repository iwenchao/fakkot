package com.iwenchaos.fakkot.ui.fragment

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.bean.FriendListResponse
import com.iwenchaos.fakkot.inflate
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * Created by chaos
 * on 2018/4/9. 21:39
 * 文件描述：
 */
class CommonUseFragment : BaseFragment() {

    private var mainView: View? = null
    private lateinit var flowLayout: ConstraintLayout

    private lateinit var hotTagFlowLayout:TagFlowLayout
    private lateinit var commonUseTagFlowLayout:TagFlowLayout
    private lateinit var bookmarkTagFlowLayout:TagFlowLayout
    private lateinit var hotTitle:TextView
    private lateinit var commonUseTitle:TextView
    private lateinit var bookmarkTitle:TextView
    private val datas = mutableListOf<FriendListResponse.Data>()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView?.let {
            mainView = inflater?.inflate(R.layout.fragment_common_use, container, false)
            flowLayout = activity.inflate(R.layout.common_hot) as ConstraintLayout
            hotTagFlowLayout = flowLayout.findViewById<TagFlowLayout>(R.id.hotFlowLayout)
            commonUseTagFlowLayout = flowLayout.findViewById<TagFlowLayout>(R.id.commonUseFlowLayout)
            bookmarkTagFlowLayout = flowLayout.findViewById<TagFlowLayout>(R.id.bookmarkFlowLayout)
        }
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun cancelRequest() {

    }
}