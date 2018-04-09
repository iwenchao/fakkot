package com.iwenchaos.fakkot.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseFragment
import com.iwenchaos.fakkot.bean.FriendListResponse

/**
 * Created by chaos
 * on 2018/4/9. 21:39
 * 文件描述：
 */
class CommonUseFragment : BaseFragment() {

    private var mainView: View? = null
    private val datas = mutableListOf<FriendListResponse.Data>()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView?.let {
            mainView = inflater?.inflate(R.layout.fragment_common_use, container, false)

        }
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun cancelRequest() {

    }
}