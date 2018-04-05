package com.iwenchaos.fakkot.ui.activity

import android.os.Bundle
import com.iwenchaos.fakkot.R
import com.iwenchaos.fakkot.base.BaseActivity
import com.just.agentweb.AgentWeb

/**
 * Created by chaos
 * on 2018/3/24. 17:53
 * 文件描述：
 */
class ContentActivity : BaseActivity() {

    private lateinit var agentWeb :AgentWeb
    private lateinit var shareTitle:String
    private lateinit var shareUrl :String
    private var  shareId : Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    /**
     * 初始化web页的immersion bar
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }


    override fun setLayoutId(): Int = R.layout.activity_content

    override fun cancelRequest() {
    }
}