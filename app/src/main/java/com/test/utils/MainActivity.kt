package com.test.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.core.util.ClipboardUtil
import com.core.util.KeyboardUtil
import com.core.util.isNotNullOrEmpty
import com.core.util.setCorner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewUtil
        findViewById<AppCompatButton>(R.id.viewUtilCorner).setCorner(50F)
        findViewById<AppCompatButton>(R.id.viewUtilCorner1).setCorner(
            floatArrayOf(
                10F,
                10F,
                20F,
                20F,
                30F,
                30F,
                40F,
                40F
            )
        )

        val data:String? = "111"
//        val result = data.isNotNullOrEmpty()
        if (data.isNotNullOrEmpty()){
            Log.d("软键盘高度监听","result=${data.length}")
        }else{

        }


        //ClipboardUtil
        findViewById<AppCompatButton>(R.id.copy).setOnClickListener {
            ClipboardUtil.putClipboard("我是复制到剪贴板的内容", "Test")
        }
        findViewById<AppCompatButton>(R.id.paste).setOnClickListener {
            findViewById<AppCompatTextView>(R.id.content).text = ClipboardUtil.getClipboard()

            Log.d("ClipboardUtil1", "${ClipboardUtil.getClipboard()}")
            Log.d("ClipboardUtil2", "${ClipboardUtil.getClipboard("Test")}")
            Log.d("ClipboardUtil3", "${ClipboardUtil.getClipboard("Test1")}")
            Log.d("ClipboardUtil4", "${ClipboardUtil.getClipboard("Test2")}")
            Log.d("ClipboardUtil5", "${ClipboardUtil.getClipboardWithoutLabel("Test")}")

            ClipboardUtil.clearClipboard()
            Log.d("ClipboardUtil6", "${ClipboardUtil.getClipboard()}")
            Log.d("ClipboardUtil7", "${ClipboardUtil.getClipboard("Test")}")
            Log.d("ClipboardUtil8", "${ClipboardUtil.getClipboard("Test1")}")
            Log.d("ClipboardUtil9", "${ClipboardUtil.getClipboard("Test2")}")

        }

        //KeyboardUtil
        val editText = findViewById<AppCompatEditText>(R.id.editText)
        findViewById<AppCompatButton>(R.id.requestFocus).setOnClickListener {
            KeyboardUtil.showSoftInput(editText)
        }
        findViewById<AppCompatButton>(R.id.dismissFocus).setOnClickListener {
            KeyboardUtil.hideSoftInput(window)
        }
        KeyboardUtil.registerSoftInputChangedListener(window,editText,object :KeyboardUtil.OnSoftInputChangedListener{
            /**
             * 键盘高度变化监听
             */
            override fun onSoftInputChanged(height: Int) {
                Log.d("软键盘高度监听","height=${height}")
            }

        })
    }
}