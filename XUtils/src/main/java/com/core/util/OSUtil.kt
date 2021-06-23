package com.core.util

import android.os.Build
import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 手机系统工具类
 * @author like
 * @date 2020/5/25 3:45 PM
 */
object OSUtil {

    private const val TAG = "Rom"

    const val ROM_MIUI = "MIUI"
    const val ROM_EMUI = "EMUI"
    const val ROM_FLYME = "FLYME"
    const val ROM_OPPO = "OPPO"
    const val ROM_SMARTISAN = "SMARTISAN"
    const val ROM_VIVO = "VIVO"
    const val ROM_QIKU = "QIKU"

    private const val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
    private const val KEY_VERSION_EMUI = "ro.build.version.emui"
    private const val KEY_VERSION_OPPO = "ro.build.version.opporom"
    private const val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
    private const val KEY_VERSION_VIVO = "ro.vivo.os.version"

    private var sName: String? = null
    private var sVersion: String? = null

    /**
     * 是否是华为手机
     */
    fun isEmui(): Boolean {
        return check(ROM_EMUI)
    }

    /**
     * 是否是小米手机
     */
    fun isMiui(): Boolean {
        return check(ROM_MIUI)
    }

    /**
     * 是否是Vivo手机
     */
    fun isVivo(): Boolean {
        return check(ROM_VIVO)
    }

    /**
     * 是否是Oppo手机
     */
    fun isOppo(): Boolean {
        return check(ROM_OPPO)
    }

    /**
     * 是否是魅族手机
     */
    fun isFlyme(): Boolean {
        return check(ROM_FLYME)
    }

    /**
     * 是否是360手机或奇酷
     */
    fun is360(): Boolean {
        return check(ROM_QIKU) || check("360")
    }

    /**
     * 是否是锤子手机
     */
    fun isSmartisan(): Boolean {
        return check(ROM_SMARTISAN)
    }

    fun getName(): String? {
        if (sName == null) {
            check("")
        }
        return sName
    }

    fun getVersion(): String? {
        if (sVersion == null) {
            check("")
        }
        return sVersion
    }

    /**
     * 检查系统
     */
    fun check(rom: String): Boolean {
        sName?.let { sName = rom }

        if (!TextUtils.isEmpty(getProp(KEY_VERSION_MIUI).also { sVersion = it })) {
            sName = ROM_MIUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_EMUI).also { sVersion = it })) {
            sName = ROM_EMUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_OPPO).also { sVersion = it })) {
            sName = ROM_OPPO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_VIVO).also { sVersion = it })) {
            sName = ROM_VIVO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_SMARTISAN).also { sVersion = it })) {
            sName = ROM_SMARTISAN
        } else {
            sVersion = Build.DISPLAY
            if (sVersion?.toUpperCase()!!.contains(ROM_FLYME)) {
                sName = ROM_FLYME
            } else {
                sVersion = Build.UNKNOWN
                sName = Build.MANUFACTURER.toUpperCase()
            }
        }
        return sName == rom
    }

    fun getProp(name: String): String? {
        var line: String? = null
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $name")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            Log.e(TAG, "Unable to read prop $name", ex)
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return line
    }

}