package com.core.util

import android.graphics.Paint
import android.text.TextUtils
import java.math.BigDecimal

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

}