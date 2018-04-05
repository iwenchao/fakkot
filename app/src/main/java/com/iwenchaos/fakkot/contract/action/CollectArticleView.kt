package com.iwenchaos.fakkot.contract.action

import com.iwenchaos.fakkot.bean.HomeListResponse

/**
 * Created by chaos
 * on 2018/4/5. 22:30
 * 文件描述：
 */
interface CollectArticleView {
    /**
     * add article success
     * @param result HomeListResponse
     * @param isAdd true add, false remove
     */
    fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean)

    /**
     * add article false
     * @param errorMessage error message
     * @param isAdd true add, false remove
     */
    fun collectArticleFailed(errorMessage: String?, isAdd: Boolean)
}