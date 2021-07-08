package com.test.utils

import android.app.Application
import com.core.XUtil

/**
 *
 * @author like
 * @date 7/8/21 1:46 PM
 */
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        XUtil.init(this)
    }

}