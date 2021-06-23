package com.core.util

import androidx.test.platform.app.InstrumentationRegistry
import com.core.XUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * 在像素密度为3的机型上测试
 * @author like
 * @date 6/21/21 5:48 PM
 */
class DensityUtilTest {

    @Before
    fun init() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        XUtil.init(appContext.applicationContext)
    }

    @Test
    fun dp2px() {
        Assert.assertEquals(6, DensityUtil.dp2px(2.0F))
    }

    @Test
    fun sp2px() {
        println(DensityUtil.sp2px(10F))
    }

    @Test
    fun px2sp() {
        println(DensityUtil.px2sp(10F))
    }

    @Test
    fun px2dp() {
        Assert.assertEquals(2, DensityUtil.px2dp(6.0F))
    }

    @Test
    fun getScreenWidth() {
        println("屏幕宽度：${DensityUtil.getScreenWidth()}")
    }

    @Test
    fun getScreenHeight() {
        println("屏幕高度：${DensityUtil.getScreenHeight()}")
    }

}