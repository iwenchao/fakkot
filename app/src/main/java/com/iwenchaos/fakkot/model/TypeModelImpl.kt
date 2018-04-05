package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.base.OnRequstCallback
import com.iwenchaos.fakkot.bean.TreeListResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.TypeContract
import com.iwenchaos.fakkot.net.RetrofitHelper
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/4/5. 19:42
 * 文件描述：
 */
class TypeModelImpl : TypeContract.TypeModel {

    private var typeAsync:Deferred<TreeListResponse>? = null

    override fun getTypeList(callback: OnRequstCallback<TreeListResponse>) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                callback.fail(it.toString())
            }){
                typeAsync?.cancelByActive()
                typeAsync = RetrofitHelper.retrofistService.getTypeList()
                val result = typeAsync?.await()
                result ?: let {
                    callback.fail(Constant.RESULT_NULL)
                    return@async
                }
                callback.success(result)
            }


        }
    }
}