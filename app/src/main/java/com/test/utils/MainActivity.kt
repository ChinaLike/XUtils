package com.test.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.core.util.setCorner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewUtil
        findViewById<AppCompatButton>(R.id.viewUtilCorner).setCorner(50F)
        findViewById<AppCompatButton>(R.id.viewUtilCorner1).setCorner(floatArrayOf(10F,10F,20F,20F,30F,30F,40F,40F))

    }
}