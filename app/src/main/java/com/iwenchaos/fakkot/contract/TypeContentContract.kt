package com.iwenchaos.fakkot.contract

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.ArticleListResponse

/**
 * Created by chaos
 * on 2018/4/6. 18:42
 * 文件描述：
 */
interface TypeContentContract {

    interface TypeModel {
        fun getTypeArticleList(cid: Int, page: Int = 0, callback: OnRequstCallback<ArticleListResponse>)
    }

    interface TypeView {

        fun getTypeArticleListSuccess(result: ArticleListResponse)
        fun getTypeArticleListFail(errorMsg: String?)

    }

    interface TypePresenter {

        fun getTypeArticleList(cid: Int, page: Int=0)
    }

}