package com.core.util

import android.graphics.Paint
import android.text.TextUtils
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * 字符串操作
 * @author like
 * @date 6/21/21 5:02 PM
 */

/**
 * 去掉末尾的0
 */
fun String?.removeZero(): String? {
    return if (TextUtils.isEmpty(this)) {
        this
    } else {
        try {
            BigDecimal(this).stripTrailingZeros().toPlainString()
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * 获取文本宽度
 * @param [textSize] 文本大小，单位px
 */
fun String?.getTextWidth(textSize: Float): Int {
    if (TextUtils.isEmpty(this)) {
        return 0
    }
    val paint = Paint()
    paint.textSize = textSize
    return paint.measureText(this).toInt()
}

/**
 * 判断字符串是否为空
 */
fun String?.isEmpty(): Boolean {
    return TextUtils.isEmpty(this)
}

/**
 * 判断字符串是否不为空
 */
fun String?.isNotEmpty(): Boolean {
    return !TextUtils.isEmpty(this)
}

/**
 * 判断是否是整数
 */
fun String?.isInteger(): Boolean {
    return if (isEmpty()) false else TextUtils.isDigitsOnly(this)
}

/**
 * 判断是否是布尔类型
 */
fun String?.isBoolean(): Boolean {
    return if (isEmpty()) false else this == "false" || this == "true"
}

/**
 * 判断是否是小数
 */
fun String?.isDecimalNumber(): Boolean {
    return if (isEmpty()) {
        false
    } else {
        //排除整数
        if (isInteger()) {
            return false
        }
        Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?").matcher(this).matches()
    }
}