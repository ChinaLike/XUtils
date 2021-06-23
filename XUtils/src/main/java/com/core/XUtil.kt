package com.core

import android.content.Context

/**
 * 初始化类
 * @author like
 * @date 6/21/21 4:55 PM
 */
object XUtil {

    private lateinit var application: Context

    fun init(application: Context) {
        this.application = application
    }

    fun getApp(): Context {
        if (!this::application.isInitialized) {
            throw NullPointerException("请在Application中主动调用XUtil.init()方法")
        }
        return application
    }

}