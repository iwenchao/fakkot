package com.iwenchaos.fakkot

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.iwenchaos.fakkot.cnostant.Constant
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.JobCancellationException

/**
 * Created by chaos
 * on 2018/3/24. 16:18
 * 文件描述：
 */

/**
 * log
 */
fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: tag)
}

/**
 *
 */
fun Context.toast(content: String) {
    Constant.showToast?.apply {
        setText(content)
        show()
    } ?: run {
        Toast.makeText(this.applicationContext, content, Toast.LENGTH_SHORT).apply {
            Constant.showToast = this
        }.show()
    }
}

fun Context.toast(@StringRes id: Int) {
    toast(getString(id))
}

fun Deferred<Any>?.cancelByActive() = this?.run {
    tryCatch {
        if (isActive) {
            cancel()
        }
    }
}

inline fun tryCatch(tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: JobCancellationException) {

    }

}

inline fun tryCatch(catchBlock: (Throwable) -> Unit, tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: JobCancellationException) {

    } catch (t: Throwable) {
        catchBlock(t)
    }
}

/**
 * LayoutInflater.from(this).inflate
 * @param resource layoutId
 * @return View
 */
fun Context.inflate(@LayoutRes resource:Int):View = LayoutInflater.from(this).inflate(resource,null)

/**
 * save cookie string
 */
fun encodeCookie(cookies : List<String>):String{
    val sb = StringBuilder()
    val set = HashSet<String>()
    cookies.map { cookie ->
        cookie.split(";".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
    }.forEach{
        it.filterNot { set.contains(it) }.forEach { set.add(it) }
    }

    val ite = set.iterator()
    while (ite.hasNext()){
        val cookie = ite.next()
        sb.append(cookie).append(";")
    }

    val last = sb.lastIndexOf(";")
    if (sb.length - 1 == last){
        sb.deleteCharAt(last)
    }
    return sb.toString()



}





