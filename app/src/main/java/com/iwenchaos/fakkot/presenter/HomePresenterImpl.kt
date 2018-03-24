package com.iwenchaos.fakkot.presenter

import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.contract.HomeContract
import com.iwenchaos.fakkot.model.HomeModelImpl

/**
 * Created by chaos
 * on 2018/3/24. 15:18
 * 文件描述：
 */
class HomePresenterImpl(private val homeView: HomeContract.View) : HomeContract.Presenter.OnHomeListListener,
        HomeContract.Presenter.OnBannerListener {


    private val homeModel : HomeContract.HomeModel = HomeModelImpl()



    override fun getHomeList(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeListFailed(errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBanner() {
        homeModel.getBanner(this)


    }

    override fun getBannerSuccess(result: BannerResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBannerFailed(errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }










}