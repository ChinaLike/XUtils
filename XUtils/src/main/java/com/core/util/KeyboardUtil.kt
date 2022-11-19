package com.core.util

import android.R
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout

/**
 * 软键盘工具类
 * @author like
 * @date 6/22/21 5:28 PM
 */
object KeyboardUtil {

    /**
     * 键盘高度
     */
    var keyboardHeight = 0

    /**
     * 监听器缓存
     */
    private val listenerArray = SparseArray<OnGlobalLayoutListener>()

    private var sDecorViewDelta = 0

    /**
     * 输入框获取焦点并打开软键盘
     */
    @JvmStatic
    fun showSoftInput(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        imm.showSoftInput(view, 0, SoftInputReceiver(view.context))
    }

    /**
     * 关闭软键盘
     */
    @JvmStatic
    fun hideSoftInput(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * 关闭软件盘
     */
    @JvmStatic
    fun hideSoftInput(window: Window) {
        var view = window.currentFocus
        if (view == null) {
            val decorView = window.decorView
            val focusView = decorView.findViewWithTag<View>("keyboardTagView")
            if (focusView == null) {
                view = EditText(window.context)
                view.setTag("keyboardTagView")
                (decorView as ViewGroup).addView(view, 0, 0)
            } else {
                view = focusView
            }
            view.requestFocus()
        }
        hideSoftInput(view)
    }

    @JvmStatic
    fun toggleSoftInput(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
        imm.toggleSoftInput(0, 0)
    }

    private fun getDecorViewInvisibleHeight(window: Window): Int {
        val decorView = window.decorView
        val outRect = Rect()
        decorView.getWindowVisibleDisplayFrame(outRect)
        val delta = Math.abs(decorView.bottom - outRect.bottom)
        if (delta <= StatusBarUtil.getNavigationBarHeight(window.context) + StatusBarUtil.getStatusBarHeight(
                window.context
            )
        ) {
            sDecorViewDelta = delta
            return 0
        }
        return delta - sDecorViewDelta
    }

    /**
     * 注册软键盘高度变化监听
     * @param [window]
     * @param [view]
     * @param [listener]
     */
    fun registerSoftInputChangedListener(
        window: Window,
        view: View,
        listener: OnSoftInputChangedListener
    ) {
        val flags = window.attributes.flags
        if (flags and WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS != 0) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        val contentView = window.findViewById<FrameLayout>(R.id.content)
        keyboardHeight = getDecorViewInvisibleHeight(window)
        val onGlobalLayoutListener = OnGlobalLayoutListener {
            val height: Int = getDecorViewInvisibleHeight(window)
            if (keyboardHeight != height) {
                listener.onSoftInputChanged(height)
                keyboardHeight = height
            }
        }
        contentView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        listenerArray.append(view.id, onGlobalLayoutListener)
    }

    /**
     * 移除键盘高度变化监听
     */
    fun removeLayoutChangeListener(window: Window, view: View) {
        val contentView = window.findViewById<View>(R.id.content) ?: return
        var tag: OnGlobalLayoutListener? = listenerArray.get(view.id)
        if (tag != null) {
            contentView.viewTreeObserver.removeOnGlobalLayoutListener(tag)
            listenerArray.remove(view.id)
        }
    }


    private class SoftInputReceiver(val context: Context) :
        ResultReceiver(Handler(Looper.getMainLooper())) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN || resultCode == InputMethodManager.RESULT_HIDDEN) {
                toggleSoftInput(context)
            }
        }
    }

    /**
     * 键盘高度变化监听器
     */
    interface OnSoftInputChangedListener {

        /**
         * 键盘高度变化监听
         */
        fun onSoftInputChanged(height: Int)

    }

}