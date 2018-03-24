package com.iwenchaos.fakkot.base

import android.app.Application
import android.content.ComponentCallbacks2
import com.bumptech.glide.Glide
import com.iwenchaos.fakkot.BuildConfig
import com.squareup.leakcanary.LeakCanary

/**
 * Created by chaos
 * on 2018/3/24. 11:39
 * 文件描述：
 */
class KotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            LeakCanary.install(this)
        }
        Preference.setContext(applicationContext)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory()
        }
        //trim memory
        Glide.get(this).trimMemory(level)

    }

    override fun onLowMemory() {
        super.onLowMemory()
        //low memory clear glide cache
        Glide.get(this).clearMemory()
    }
}