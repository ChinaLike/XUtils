package com.core.util

import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.core.XUtil

/**
 * 资源文件有关
 * @author like
 * @date 6/22/21 2:35 PM
 */
object ResourceUtil {

    /**
     * 获取颜色
     * @param [resId] 颜色资源文件
     */
    fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(XUtil.getApp(), resId)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     */
    fun getString(@StringRes resID: Int): String {
        return XUtil.getApp().resources.getString(resID)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [strings] 格式参数
     */
    fun getString(@StringRes resID: Int, strings: String?): String {
        return XUtil.getApp().resources.getString(resID, strings)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [num] 格式参数
     */
    fun getString(@StringRes resID: Int, num: Int): String {
        return XUtil.getApp().resources.getString(resID, num)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [content] 多个格式化参数
     */
    fun getString(@StringRes resID: Int, vararg content: Any?): String {
        return XUtil.getApp().resources.getString(resID, *content)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     */
    fun getString(@StringRes resID: Int, one: String?, two: String?, three: String?): String {
        return XUtil.getApp().resources.getString(resID, one, two, three)
    }

    /**
     * 获取字符串数组
     */
    fun getStringArray(@ArrayRes resID: Int): Array<String> {
        return XUtil.getApp().resources.getStringArray(resID)
    }

    /**
     * 获取Drawable文件对象
     */
    fun getDrawable(@DrawableRes resID: Int): Drawable? {
        return ContextCompat.getDrawable(XUtil.getApp(), resID)
    }

    /**
     * 获取尺寸有关
     */
    fun getDimension(@DimenRes resID: Int): Int {
        return XUtil.getApp().resources.getDimension(resID).toInt()
    }

}