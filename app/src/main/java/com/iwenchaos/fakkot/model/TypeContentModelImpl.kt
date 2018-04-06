package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.ArticleListResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.TypeContentContract
import com.iwenchaos.fakkot.net.RetrofitHelper
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/4/6. 18:46
 * 文件描述：
 */
class TypeContentModelImpl : TypeContentContract.TypeModel {

    private var contentAsync: Deferred<ArticleListResponse>? = null

    override fun getTypeArticleList(cid: Int, page: Int, callback: OnRequstCallback<ArticleListResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }) {
                contentAsync?.cancelByActive()
                contentAsync = RetrofitHelper.retrofistService.getArticleList(page, cid)

                val result = contentAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }
        }
    }
}