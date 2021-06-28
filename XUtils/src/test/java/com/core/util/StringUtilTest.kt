package com.core.util

import org.junit.Assert
import org.junit.Test

/**
 *
 * @author like
 * @date 6/21/21 5:23 PM
 */
class StringUtilTest {

    @Test
    fun removeZero(){
        Assert.assertEquals("8.1",StringUtil.removeZero("8.100"))
        Assert.assertEquals("7",StringUtil.removeZero("7.0"))
    }

}