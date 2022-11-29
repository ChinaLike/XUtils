package com.core.util

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * 颜色工具类
 * @author like
 * @date 2022/11/29 16:25
 */
object ColorUtil {

    /**
     * 颜色转换
     * @param [colorStr] 需要转换的字符串
     * @param [defaultColor] 转换失败的默认值，默认白色
     */
    @JvmStatic
    @JvmOverloads
    @ColorInt
    fun parseColor(colorStr: String, defaultColor: Int = Color.WHITE): Int {
        return try {
            Color.parseColor(colorStr)
        } catch (e: IllegalArgumentException) {
            defaultColor
        }
    }
}