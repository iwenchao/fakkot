package com.iwenchaos.fakkot.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chaos
 * on 2018/3/24. 22:44
 * 文件描述：
 */
class RetrofitBuilder (
        url: String, client: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
){
    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(url)
        client(client)
        addConverterFactory(gsonFactory)
        addCallAdapterFactory(coroutineCallAdapterFactory)
    }.build()
}