package com.iwenchaos.fakkot.base

import android.support.v4.app.Fragment

/**
 * Created by chaos
 * on 2018/3/24. 14:08
 * 文件描述：
 */
abstract class BaseFragment : Fragment() {

    protected var isFirst: Boolean = true

    protected abstract fun cancelRequest()

    override fun onDestroyView() {
        super.onDestroyView()
        cancelRequest()
    }

}


