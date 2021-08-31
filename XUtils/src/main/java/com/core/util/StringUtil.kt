package com.core.util

import android.graphics.Paint
import android.text.TextUtils
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * 字符串
 * @author like
 * @date 6/21/21 5:02 PM
 */
object StringUtil {

    /**
     * 去掉末尾的0
     */
    fun removeZero(formatString: String): String {
        if (formatString.isNotEmpty()) {
            return BigDecimal(formatString).stripTrailingZeros().toPlainString()
        }
        return formatString
    }

    /**
     * 获取文本宽度
     * @param [textSize] 文本大小，单位px
     * @param [text] 文本
     */
    fun getTextWidth(textSize: Float, text: String?): Int {
        if (TextUtils.isEmpty(text)) {
            return 0
        }
        val paint = Paint()
        paint.textSize = textSize
        return paint.measureText(text).toInt()
    }

    fun isEmpty(str: String?): Boolean {
        return if (str == null) {
            true
        } else {
            str!!.isEmpty()
        }
    }

    /**
     * 判断是否是整数
     */
    fun isInteger(str: String?): Boolean {
        return if (isEmpty(str)) {
            false
        } else {
            TextUtils.isDigitsOnly(str)
        }
    }

    /**
     * 判断是否是布尔类型
     */
    fun isBoolean(str: String?): Boolean {
        return if (isEmpty(str)) {
            false
        } else {
            str == "false" || str == "true"
        }
    }

    /**
     * 判断是否是小数
     */
    fun isDecimalNumber(str: String?): Boolean {
        return if (isEmpty(str)) {
            false
        } else {
            //排除整数
            if (isInteger(str)){
                return false
            }
            val pattern = Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?")
            pattern.matcher(str).matches()
        }
    }

}