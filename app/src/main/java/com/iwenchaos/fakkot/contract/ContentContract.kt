package com.iwenchaos.fakkot.contract

/**
 * Created by chaos
 * on 2018/4/5. 22:05
 * 文件描述：
 */
interface ContentContract {


    interface ContentModel {
        fun collectOutsideArticle()
    }

    interface ContentView {


    }

    interface ContentPresenter {

        fun collectOutSideArticle(title: String,
                                  author: String,
                                  link: String,
                                  isAdd: Boolean)
    }


}