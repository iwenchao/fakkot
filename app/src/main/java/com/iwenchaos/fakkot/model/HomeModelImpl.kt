package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.contract.HomeContract
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/3/24. 15:21
 * 文件描述：
 */
class HomeModelImpl :HomeContract.HomeModel {


    /**
     * Home list async
     */
    private var homeListAsync: Deferred<HomeListResponse>? = null





    override fun getHomeList(onHomeListListener: HomeContract.Presenter.OnHomeListListener, page: Int) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                onHomeListListener.getHomeListFailed(it.toString())
            }){
                homeListAsync?.cancelByActive()
//                homeListAsync =
            }
        }
    }

}