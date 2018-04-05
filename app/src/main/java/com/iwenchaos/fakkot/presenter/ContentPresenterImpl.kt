package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.contract.ContentContract
import com.iwenchaos.fakkot.contract.action.CollectArticleView

/**
 * Created by chaos
 * on 2018/4/5. 22:34
 * 文件描述：
 */
class ContentPresenterImpl(private val collectArticleView: CollectArticleView) : ContentContract.ContentPresenter {




    override fun collectOutSideArticle(title: String, author: String, link: String, isAdd: Boolean) {

    }
}