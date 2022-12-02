package com.core.util

import android.content.Context
import android.content.res.ColorStateList
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
    @JvmOverloads
    @JvmStatic
    fun getColor(@ColorRes resId: Int, context: Context = XUtil.getApp()): Int {
        return ContextCompat.getColor(context, resId)
    }

    /**
     * 获取颜色组
     * @param [resId] 颜色资源文件
     */
    @JvmOverloads
    @JvmStatic
    fun getColorStateList(
        @ColorRes resId: Int,
        context: Context = XUtil.getApp()
    ): ColorStateList? {
        return ContextCompat.getColorStateList(context, resId)
    }


    /**
     * 获取字符串
     * @param [resID] 字符串ID
     */
    @JvmOverloads
    @JvmStatic
    fun getString(@StringRes resID: Int, context: Context = XUtil.getApp()): String {
        return context.resources.getString(resID)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [strings] 格式参数
     */
    @JvmOverloads
    @JvmStatic
    fun getString(
        @StringRes resID: Int,
        strings: String?,
        context: Context = XUtil.getApp()
    ): String {
        return context.resources.getString(resID, strings)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [num] 格式参数
     */
    @JvmOverloads
    @JvmStatic
    fun getString(@StringRes resID: Int, num: Int, context: Context = XUtil.getApp()): String {
        return context.resources.getString(resID, num)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     * @param [content] 多个格式化参数
     */
    @JvmOverloads
    @JvmStatic
    fun getString(
        @StringRes resID: Int,
        vararg content: Any?,
        context: Context = XUtil.getApp()
    ): String {
        return context.resources.getString(resID, *content)
    }

    /**
     * 获取字符串
     * @param [resID] 字符串ID
     */
    @JvmOverloads
    @JvmStatic
    fun getString(
        @StringRes resID: Int,
        one: String?,
        two: String?,
        three: String?,
        context: Context = XUtil.getApp()
    ): String {
        return context.resources.getString(resID, one, two, three)
    }

    /**
     * 获取字符串数组
     */
    @JvmOverloads
    @JvmStatic
    fun getStringArray(@ArrayRes resID: Int, context: Context = XUtil.getApp()): Array<String> {
        return context.resources.getStringArray(resID)
    }

    /**
     * 获取Drawable文件对象
     */
    @JvmOverloads
    @JvmStatic
    fun getDrawable(@DrawableRes resID: Int, context: Context = XUtil.getApp()): Drawable? {
        return ContextCompat.getDrawable(context, resID)
    }

    /**
     * 获取尺寸有关
     */
    @JvmOverloads
    @JvmStatic
    fun getDimension(@DimenRes resID: Int, context: Context = XUtil.getApp()): Int {
        return context.resources.getDimension(resID).toInt()
    }

}