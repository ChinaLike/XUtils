package com.core.util

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * 数据处理有关的工具类
 * @author like
 * @date 6/22/21 5:47 PM
 */
object DataUtil {

    private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F')

    /**
     * byteArr转hexString
     *
     * 例如：
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes byte数组
     * @return 16进制大写字符串
     */
    @JvmStatic
    fun bytes2HexString(bytes: ByteArray): String {
        val ret = CharArray(bytes.size shl 1)
        var i = 0
        var j = 0
        while (i < bytes.size) {
            ret[j++] = HEX_DIGITS[bytes[i].toInt() ushr 4 and 0x0f]
            ret[j++] = HEX_DIGITS[bytes[i].toInt() and 0x0f]
            i++
        }
        return String(ret)
    }

    /**
     * hexString转byteArr
     *
     * 例如：
     * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    @JvmStatic
    fun hexString2Bytes(hexString: String): ByteArray {
        val len = hexString.length
        require(len % 2 == 0) { "长度不是偶数" }
        val hexBytes = hexString.toUpperCase().toCharArray()
        val ret = ByteArray(len ushr 1)
        var i = 0
        while (i < len) {
            ret[i shr 1] = (hex2Dec(hexBytes[i]) shl 4 or hex2Dec(hexBytes[i + 1])).toByte()
            i += 2
        }
        return ret
    }

    /**
     * hexChar转int
     *
     * @param hexChar hex单个字节
     * @return 0..15
     */
    private fun hex2Dec(hexChar: Char): Int {
        return when (hexChar) {
            in '0'..'9' -> {
                hexChar - '0'
            }
            in 'A'..'F' -> {
                hexChar - 'A' + 10
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    /**
     * 字节数转合适大小
     *
     * 保留3位小数
     *
     * @param byteNum 字节数
     * @return 1...1024 unit
     */
    @JvmStatic
    fun byte2FitSize(byteNum: Long): String {
        return if (byteNum < 0) {
            "shouldn't be less than zero!"
        } else if (byteNum < ConstUtil.KB) {
            String.format(Locale.getDefault(), "%.3fB", byteNum.toDouble())
        } else if (byteNum < ConstUtil.MB) {
            String.format(Locale.getDefault(), "%.3fKB", byteNum.toDouble() / ConstUtil.KB)
        } else if (byteNum < ConstUtil.GB) {
            String.format(Locale.getDefault(), "%.3fMB", byteNum.toDouble() / ConstUtil.MB)
        } else {
            String.format(Locale.getDefault(), "%.3fGB", byteNum.toDouble() / ConstUtil.GB)
        }
    }

    /**
     * inputStream转byteArr
     *
     * @param is 输入流
     * @return 字节数组
     */
    @JvmStatic
    fun inputStream2Bytes(`is`: InputStream?): ByteArray {
        return input2OutputStream(`is`)!!.toByteArray()
    }

    /**
     * inputStream转outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */
    @JvmStatic
    fun input2OutputStream(`is`: InputStream?): ByteArrayOutputStream? {
        return if (`is` == null) {
            null
        } else try {
            val os = ByteArrayOutputStream()
            val b = ByteArray(ConstUtil.KB)
            var len: Int
            while (`is`.read(b, 0, ConstUtil.KB).also { len = it } != -1) {
                os.write(b, 0, len)
            }
            os
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            FileUtil.closeIO(`is`)
        }
    }

    /**
     * 字符串转换成整数 ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    @JvmStatic
    fun stringToInt(str: String): Int {
        return if (str.isNullOrEmpty()) {
            0
        } else {
            try {
                str.toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
    }
}