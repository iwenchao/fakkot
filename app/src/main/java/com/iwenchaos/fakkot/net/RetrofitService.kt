package com.iwenchaos.fakkot.net

import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.HomeListResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by chaos
 * on 2018/3/24. 20:04
 * 文件描述：接口api
 */
interface RetrofitService {
    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     * @param page page
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Deferred<HomeListResponse>


    @GET("/banner/json")
    fun getBanner():Deferred<BannerResponse>
}