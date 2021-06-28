package com.core.util

/**
 *
 * @author like
 * @date 6/23/21 5:46 PM
 */
//object ThreadUtil {
//
//
//
//}

fun runThread(callback: ThreadCallback) {
    Thread(Runnable {
        callback?.run()
    }).start()
}


interface ThreadCallback {
    fun run()
}
