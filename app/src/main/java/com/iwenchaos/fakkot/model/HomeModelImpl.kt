package com.iwenchaos.fakkot.model

import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.cancelByActive
import com.iwenchaos.fakkot.cnostant.Constant
import com.iwenchaos.fakkot.contract.HomeContract
import com.iwenchaos.fakkot.net.RetrofitHelper
import com.iwenchaos.fakkot.tryCatch
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

/**
 * Created by chaos
 * on 2018/3/24. 15:21
 * 文件描述：
 */
class HomeModelImpl :HomeContract.HomeModel{
    /**
     * Home list async
     */
    private var homeListAsync: Deferred<HomeListResponse>? = null

    private var bannerAsync:Deferred<BannerResponse>? = null
    override fun getHomeList(onHomeListListener: HomeContract.Presenter.OnHomeListListener, page: Int) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                onHomeListListener.getHomeListFailed(it.toString())
            }){
                homeListAsync?.cancelByActive()
                homeListAsync = RetrofitHelper.retrofistService.getHomeList(page)
                //get async result 异步
                val result = homeListAsync?.await()
                result ?: let {
                    onHomeListListener.getHomeListFailed(Constant.RESULT_NULL)
                    return@async
                }
                onHomeListListener.getHomeListSuccess(result)
            }
        }
    }


    override fun getBanner(onBannerListener: HomeContract.Presenter.OnBannerListener) {
        async(UI){
            tryCatch({
                it.printStackTrace()
                onBannerListener.getBannerFailed(it.toString())
            }){
                bannerAsync?.cancelByActive()
                bannerAsync = RetrofitHelper.retrofistService.getBanner()
                val result = bannerAsync?.await()
                result ?: let {
                    onBannerListener.getBannerFailed(Constant.RESULT_NULL)
                    return@async
                }
                onBannerListener.getBannerSuccess(result)
            }
        }
    }


    override fun cancelBannerRequest() {
        bannerAsync?.cancelByActive()
    }

}
