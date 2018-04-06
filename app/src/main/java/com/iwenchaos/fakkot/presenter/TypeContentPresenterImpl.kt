package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.ArticleListResponse
import com.iwenchaos.fakkot.contract.TypeContentContract
import com.iwenchaos.fakkot.model.TypeContentModelImpl

/**
 * Created by chaos
 * on 2018/4/6. 18:44
 * 文件描述：
 */
class TypeContentPresenterImpl(val view: TypeContentContract.TypeView) : TypeContentContract.TypePresenter {

    private val model = TypeContentModelImpl()

    override fun getTypeArticleList(cid: Int, page: Int) {
        model.getTypeArticleList(cid, page, object : OnRequstCallback<ArticleListResponse> {
            /**
             * 请求成功
             */
            override fun success(result: ArticleListResponse) {
                if (result.errorCode != 0){
                    view.getTypeArticleListFail(result.errorMsg)
                }else{
                    view.getTypeArticleListSuccess(result)
                }
            }

            /**
             * 请求失败
             */
            override fun fail(errorMsg: String) {
                view.getTypeArticleListFail(errorMsg)
            }

        })
    }
}