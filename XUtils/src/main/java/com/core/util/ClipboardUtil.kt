package com.core.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import com.core.XUtil

/**
 * 剪贴板
 * @author like
 * @date 7/8/21 11:18 AM
 */
object ClipboardUtil {

    /**
     * 获取剪贴板内容
     * @param [label] Label标签，默认获取所有，如果传自己的，会根据label过滤数据
     */
    fun getClipboard(label: String? = null): String? {
        val clipboardManager =
            XUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        val clipboardLabel = clipboardManager?.primaryClipDescription?.label
        if (clipboardManager?.hasPrimaryClip() == true && (label == null || label == clipboardLabel)) {
            if (clipboardManager.primaryClip!!.itemCount > 0) {
                return clipboardManager.primaryClip!!.getItemAt(0).text?.toString()
            }
            return null
        }
        return null
    }

    /**
     * 获取剪贴板内容
     * @param [label] Label标签,通过这个值过滤不包含Label的数据
     */
    fun getClipboardWithoutLabel(label: String):String?{
        val clipboardManager =
            XUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        val clipboardLabel = clipboardManager?.primaryClipDescription?.label
        if (clipboardLabel != label && clipboardManager?.hasPrimaryClip() == true){
            if (clipboardManager.primaryClip!!.itemCount > 0) {
                return clipboardManager.primaryClip!!.getItemAt(0).text?.toString()
            }
            return null
        }
        return null
    }

    /**
     * 复制数据到剪贴板
     * @param [text] 文本数据
     * @param [label] 自定义标签
     */
    fun putClipboard(text: String, label: String? = null) {
        val clipboardManager =
            XUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboardManager?.setPrimaryClip(ClipData.newPlainText(label, text))
    }

    /**
     * 清空剪贴板
     */
    fun clearClipboard() {
        val clipboardManager =
            XUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboardManager?.apply {
            primaryClip?.let { setPrimaryClip(it) }
            setPrimaryClip(ClipData.newPlainText(null, null))
        }
    }

}