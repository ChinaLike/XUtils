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
    fun add(){
        Assert.assertEquals(8,StringUtil.add(3,5))
        Assert.assertEquals(7,StringUtil.add(3,4))
    }

    @Test
    fun add1(){
        Assert.assertEquals(2,StringUtil.add(1,1))
    }

}