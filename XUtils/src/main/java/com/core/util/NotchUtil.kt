package com.core.util

import android.app.Activity
import android.os.Build

/**
 * 对于异型屏的处理
 * @author like
 * @date 2020/5/25 3:50 PM
 */
object NotchUtil {

    /**
     * OPPO检测凹形屏，适配 PAAM00， PAAT00， PACM00，PACT00，CPH1831，CPH1833上述机型的屏幕规格完全相同，不需分别做差异化处理，统一适配即可。
     * https://open.oppomobile.com/wiki/doc#id=10159
     */
    private const val KEY_OPPO = "com.oppo.feature.screen.heteromorphism"

    /**
     * VIVO检测是否有凹槽或圆角
     * https://swsdl.vivo.com.cn/appstore/developer/uploadfile/20180328/20180328152252602.pdf
     */
    private const val KEY_VIVO = "android.util.FtFeature"

    /**
     * 小米检测是否有凹槽
     * https://dev.mi.com/console/doc/detail?pId=1293
     */
    private const val KEY_MIUI = "android.os.SystemProperties"

    /**
     * 华为检测是否有凹槽
     */
    private const val KEY_EMUI = "com.huawei.android.util.HwNotchSizeUtil"


    /**
     * 是否是异型屏幕
     */
    fun hasNotchInScreen(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //android P及以上通过系统API获取
            var displayCutout = activity.window.decorView.rootWindowInsets.displayCutout
            displayCutout?.let {
                return true
            }
        } else {
            //android P以下
            if (OSUtil.isEmui()) {
                return hasNotchEMUI(activity)
            } else if (OSUtil.isMiui()) {
                return hasNotchMIUI()
            } else if (OSUtil.isOppo()) {
                //OPPO机型
                return hasNotchOPPO(activity)
            } else if (OSUtil.isVivo()) {
                return hasNotchVIVO(0x00000020)
            }
        }
        return false
    }

    /**
     * VIVO检测屏幕是否有凹槽
     * @param [mask] 0x00000020表示是否有凹槽  0x00000008表示是否有圆角
     * @return true表示具备此特性 false表示没有此特性
     */
    private fun hasNotchVIVO(mask: Int): Boolean {
        try {
            val clazz = Class.forName(KEY_VIVO)
            val method = clazz.getMethod("isFeatureSupport", Int.javaClass)
            var result = method.invoke(clazz, mask)
            if (result is Boolean) {
                return result
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * OPPO检测屏幕是否有凹槽
     */
    private fun hasNotchOPPO(activity: Activity): Boolean {
        return activity.packageManager.hasSystemFeature(KEY_OPPO)
    }

    /**
     * 小米检测屏幕是否有凹槽
     */
    private fun hasNotchMIUI(): Boolean {
        try {
            val clazz = Class.forName(KEY_MIUI)
            val method = clazz.getMethod("getInt", String.javaClass, Int.javaClass)
            var result = method.invoke(clazz, "ro.miui.notch", 0)
            if (result is Int) {
                return result == 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 华为检测是否有凹槽
     */
    private fun hasNotchEMUI(activity: Activity): Boolean {
        try {
            val classLoader = activity.classLoader
            val clazz = classLoader.loadClass(KEY_EMUI)
            val method = clazz.getMethod("hasNotchInScreen")
            var result = method.invoke(clazz)
            if (result is Boolean) {
                return result
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

}