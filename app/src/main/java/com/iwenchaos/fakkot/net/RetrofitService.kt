package com.iwenchaos.fakkot.net

import com.iwenchaos.fakkot.bean.BannerResponse
import com.iwenchaos.fakkot.bean.HomeListResponse
import com.iwenchaos.fakkot.bean.LoginResponse
import com.iwenchaos.fakkot.bean.TreeListResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

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
    fun getBanner(): Deferred<BannerResponse>

    /**
     * 用户登录接口
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun doLogin(@Field("username") username: String,
                @Field("password") password: String): Deferred<LoginResponse>


    @POST("/user/register")
    @FormUrlEncoded
    fun doRegister(@Field("username") username: String,
                   @Field("password") password: String,
                   @Field("repassword") repassword: String): Deferred<LoginResponse>


    @GET("/tree/json")
    fun getTypeList(): Deferred<TreeListResponse>


    /**
     * 收藏外站文章
     */
    @POST("/lg/collect/add/json")
    fun addCollectOutSideArticle(
            @Field("title") title: String,
            @Field("author") author: String,
            @Field("link") link: String
    ): Deferred<HomeListResponse>
}