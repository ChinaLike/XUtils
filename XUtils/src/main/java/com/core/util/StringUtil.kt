package com.core.util

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
        return BigDecimal(formatString).stripTrailingZeros().toPlainString()
    }

}