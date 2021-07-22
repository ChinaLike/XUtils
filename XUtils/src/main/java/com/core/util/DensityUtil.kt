package com.core.util

import android.content.Context
import com.core.XUtil

/**
 * 密度有关工具类
 * @author like
 * @date 6/21/21 5:38 PM
 */
object DensityUtil {

    /**
     * 单位转换，dp转px
     * @param [dpValue] dp值
     */
    fun dp2px(dpValue: Float): Int {
        return (dpValue * XUtil.getApp().resources.displayMetrics.density + 0.5F).toInt()
    }

    /**
     * 单位转换，dp转px
     * @param [dpValue] dp值
     * @param [context] 上下文
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        return (dpValue * context.resources.displayMetrics.density + 0.5F).toInt()
    }

    /**
     * 单位转换，px转dp
     * @param [pxValue] px值
     */
    fun px2dp(pxValue: Float): Int {
        return (pxValue / XUtil.getApp().resources.displayMetrics.density + 0.5F).toInt()
    }

    /**
     * 单位转换，px转dp
     * @param [context] 上下文
     * @param [pxValue] px值
     */
    fun px2dp(context: Context,pxValue: Float): Int {
        return (pxValue / context.resources.displayMetrics.density + 0.5F).toInt()
    }

    /**
     * 单位转换，sp转px
     * @param [spValue] sp值
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = XUtil.getApp().resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5F).toInt()
    }

    /**
     * 单位转换，sp转px
     * @param [context] 上下文
     * @param [spValue] sp值
     */
    fun sp2px(context: Context,spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5F).toInt()
    }

    /**
     * 单位转换，px转sp
     * @param [pxValue] sp值
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = XUtil.getApp().resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5F).toInt()
    }

    /**
     * 单位转换，px转sp
     * @param [context] 上下文
     * @param [pxValue] sp值
     */
    fun px2sp(context: Context,pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5F).toInt()
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(): Int {
        return XUtil.getApp().resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕宽度
     * @param [context] 上下文
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(): Int {
        return XUtil.getApp().resources.displayMetrics.heightPixels
    }

    /**
     * 获取屏幕高度
     * @param [context] 上下文
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

}