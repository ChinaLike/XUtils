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
        Assert.assertEquals(true,StringUtil.isInteger("7"))
        Assert.assertEquals(true,StringUtil.isBoolean("false"))
        Assert.assertEquals(true,StringUtil.isDecimalNumber("7.1"))

        Assert.assertEquals(false,StringUtil.isInteger("7.0"))
        Assert.assertEquals(false,StringUtil.isBoolean("12"))
        Assert.assertEquals(false,StringUtil.isDecimalNumber("222"))
    }

}