package com.iwenchaos.fakkot.contract

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.HomeListResponse

/**
 * Created by chaos
 * on 2018/4/5. 22:05
 * 文件描述：
 */
interface ContentContract {


    interface ContentModel {
        fun collectOutsideArticle(title: String,
                                  author: String,
                                  link: String,
                                  isAdd: Boolean,
                                  callback: OnRequstCallback<HomeListResponse>)


    }

    interface ContentView {


    }

    interface ContentPresenter {

        fun collectOutSideArticle(title: String,
                                  author: String,
                                  link: String,
                                  isAdd: Boolean)

        fun collectArticle(shareId: Int, boolean: Boolean)
    }


}