package com.iwenchaos.fakkot.ui.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by chaos
 * on 2018/3/24. 17:04
 * 文件描述：
 */
class HorizontalRecyclerView : RecyclerView {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)//子view希望parent不要拦截事件
        return super.dispatchTouchEvent(ev)
    }

}