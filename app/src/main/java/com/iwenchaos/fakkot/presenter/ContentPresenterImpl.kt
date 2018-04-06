package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.contract.ContentContract
import com.iwenchaos.fakkot.contract.action.CollectArticleView
import com.iwenchaos.fakkot.model.ContentModelImpl

/**
 * Created by chaos
 * on 2018/4/5. 22:34
 * 文件描述：
 */
class ContentPresenterImpl(private val collectArticleView: CollectArticleView) : ContentContract.ContentPresenter {

    private val collectModelImpl = ContentModelImpl()


    override fun collectOutSideArticle(title: String, author: String, link: String, isAdd: Boolean) {
        collectModelImpl.collectOutsideArticle(title, author, link, isAdd, object : OnRequstCallback<HomeListResponse> {
            /**
             * 请求成功
             */
            override fun success(result: HomeListResponse) {
                if (result.errorCode != 0) {
                    collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
                } else {
                    collectArticleView.collectArticleSuccess(result, isAdd)
                }
            }

            /**
             * 请求失败
             */
            override fun fail(errorMsg: String) {
                collectArticleView.collectArticleFailed(errorMsg, isAdd)
            }

        })
    }


    override fun collectArticle(shareId: Int, isAdd: Boolean) {
        collectModelImpl.collectArticle(shareId, isAdd, object : OnRequstCallback<HomeListResponse> {
            /**
             * 请求成功
             */
            override fun success(result: HomeListResponse) {
                collectArticleView.collectArticleSuccess(result, isAdd)
            }

            /**
             * 请求失败
             */
            override fun fail(errorMsg: String) {
                collectArticleView.collectArticleFailed(errorMsg, isAdd)
            }

        })

    }
}