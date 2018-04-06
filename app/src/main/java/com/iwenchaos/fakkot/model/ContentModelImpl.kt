package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.ContentContract
import com.iwenchaos.fakkot.net.RetrofitHelper
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/4/6. 09:39
 * 文件描述：
 */
class ContentModelImpl : ContentContract.ContentModel {
    private var articleAsync: Deferred<HomeListResponse>? = null

    override fun collectOutsideArticle(title: String, author: String,
                                       link: String, isAdd: Boolean, callback: OnRequstCallback<HomeListResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }) {
                articleAsync?.cancelByActive()
                articleAsync = RetrofitHelper.retrofistService.addCollectOutSideArticle(title, author, link)

                val result = articleAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }
        }

    }


    override fun collectArticle(id: Int, isAdd: Boolean, callback: OnRequstCallback<HomeListResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }) {
                articleAsync?.cancelByActive()
                articleAsync = if (isAdd){
                    RetrofitHelper.retrofistService.addCollectArticle(id)
                }else{
                    RetrofitHelper.retrofistService.removeCollectArticle(id)
                }

                val result = articleAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }
        }

    }
}