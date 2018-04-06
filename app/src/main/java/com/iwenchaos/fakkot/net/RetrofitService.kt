package com.iwenchaos.fakkot.net

import com.iwenchaos.fakkot.bean.*
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


    /**
     * 收藏文章
     * @param id id
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/collect/{id}/json")
    fun addCollectArticle(
            @Path("id") id: Int
    ): Deferred<HomeListResponse>

    /**
     * 删除收藏文章
     * @param id id
     * @param originId -1
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun removeCollectArticle(
            @Path("id") id: Int,
            @Field("originId") originId: Int = -1
    ): Deferred<HomeListResponse>

    @GET("/article/list/{page}/json")
    fun getArticleList(@Query("cid") cid: Int,
                       @Path("path") page: Int): Deferred<ArticleListResponse>
}