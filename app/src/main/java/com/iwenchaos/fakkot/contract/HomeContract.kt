package com.iwenchaos.fakkot.contract

import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.HomeListResponse

/**
 * Created by chaos
 * on 2018/3/24. 14:11
 * 文件描述：
 */
interface HomeContract {

    interface View{
        /**
         * get Home list Success
         */
        fun getHomeListSuccess(result: HomeListResponse)

        /**
         * get Home list Failed
         */
        fun getHomeListFailed(errorMessage: String?)

        /**
         * get Home list data size equal zero
         */
        fun getHomeListZero()

        /**
         * get Home list data less than 20
         */
        fun getHomeListSmall(result: HomeListResponse)

        /**
         * get Banner Success
         * @param result BannerResponse
         */
        fun getBannerSuccess(result: BannerResponse)

        /**
         * get Banner Failed
         * @param errorMessage error message
         */
        fun getBannerFailed(errorMessage: String?)

        /**
         * get Banner data size equal zero
         */
        fun getBannerZero()
    }


    interface Presenter{
        /**
         * get home list interface
         */
        interface OnHomeListListener {

            /**
             * get home list
             * @param page page
             */
            fun getHomeList(page: Int = 0)

            /**
             * get home list success
             * @param result result
             */
            fun getHomeListSuccess(result: HomeListResponse)

            /**
             * get home list failed
             * @param errorMessage error message
             */
            fun getHomeListFailed(errorMessage: String?)
        }


        /**
         * get banner listener
         */
        interface OnBannerListener {
            /**
             * get banner
             */
            fun getBanner()

            /**
             * get banner success
             * @param result BannerResponse
             */
            fun getBannerSuccess(result: BannerResponse)

            /**
             * get banner failed
             * @param errorMessage error message
             */
            fun getBannerFailed(errorMessage: String?)
        }


    }


    /**
     * 首页Model接口
     */
    interface HomeModel {
        /**
         * get Home List
         * @param onHomeListListener HomePresenter.OnHomeListListener
         * @param page page
         */
        fun getHomeList(onHomeListListener: Presenter.OnHomeListListener, page: Int = 0)

        /**
         * get banner
         * @param onBannerListener HomePresenter.OnBannerListener
         */
        fun getBanner(onBannerListener: Presenter.OnBannerListener)
    }
}