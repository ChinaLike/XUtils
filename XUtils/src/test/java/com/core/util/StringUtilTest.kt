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
        Assert.assertEquals("8.1","8.100".removeZero())
        Assert.assertEquals("7","7.0".removeZero())
        Assert.assertEquals(true,"7".isInteger())
        Assert.assertEquals(true,"false".isBoolean())
        Assert.assertEquals(true,"7.1".isDecimalNumber())

        Assert.assertEquals(false,"7.0".isInteger())
        Assert.assertEquals(false,"12".isBoolean())
        Assert.assertEquals(false,"222".isDecimalNumber())
    }

}