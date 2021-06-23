package com.core.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import java.util.*
import kotlin.system.exitProcess

/**
 * Activity工具类
 * @author like
 * @date 2020/5/16 4:15 PM
 */
object ActivityUtil {

    var activityStack: Stack<Activity?>? = null


    /**
     * 判断Activity是否被销毁
     * @param [activity] activity
     */
    fun isDestroy(activity: AppCompatActivity): Boolean {
        activity?.run {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                isFinishing.or(supportFragmentManager == null)
                    .or(supportFragmentManager.isDestroyed)
            } else {
                isFinishing.or(isDestroyed)
            }
        }
        return true
    }

    /**
     * 添加Activity 到栈
     *
     * @param activity Activity
     */
    @JvmStatic
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    /**
     * 从List中移除活动
     *
     * @param activity 活动
     */
    @JvmStatic
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            if (activityStack!!.contains(activity)) {
                activityStack?.remove(activity)
            }
        }
    }


    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    @JvmStatic
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    @JvmStatic
    @JvmOverloads
    fun finishActivity(isTransition: Boolean = false) {
        val activity = activityStack?.lastElement()
        if (isTransition) {
            activity?.onBackPressed()
        } else {
            activity?.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     */
    @JvmStatic
    fun finishActivity(cls: Class<out Activity>) {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有的Activity
     */
    @JvmStatic
    fun finishAllActivity() {
        val size = activityStack!!.size
        for (i in 0 until size) {
            if (null != activityStack!![i]) {
                activityStack!![i]!!.finish()
            }
        }
        activityStack!!.clear()
    }

    @Suppress("DEPRECATION")
    @JvmStatic
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.restartPackage(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 判断是否存在指定Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return `true`: 是<br></br>`false`: 否
     */
    @JvmStatic
    fun isExistActivity(context: Context, packageName: String?, className: String?): Boolean {
        val intent = Intent()
        intent.setClassName(packageName!!, className!!)
        return !(context.packageManager.resolveActivity(
            intent,
            0
        ) == null || intent.resolveActivity(context.packageManager) == null || context.packageManager.queryIntentActivities(
            intent,
            0
        ).size == 0)
    }


    @JvmStatic
    @JvmOverloads
    fun skipActivityForResult(
        context: Activity,
        goal: Class<out Activity>?,
        bundle: Bundle? = null,
        requestCode: Int
    ) {
        val intent = Intent(context, goal)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivityForResult(intent, requestCode)
    }

    @JvmStatic
    @JvmOverloads
    fun skipActivityTransition(
        mContext: Context,
        goal: Class<out Activity>?,
        bundle: Bundle? = null,
        view: View?,
        elementName: String?
    ) {
        val intent = Intent(mContext, goal)
        val bundle1 = ActivityOptionsCompat.makeSceneTransitionAnimation(
            (mContext as Activity),
            view!!,
            elementName!!
        ).toBundle()
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        mContext.startActivity(intent, bundle1)
    }

    /**
     * 获取launcher activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @return launcher activity
     */
    @JvmStatic
    fun getLauncherActivity(context: Context, packageName: String): String {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pm = context.packageManager
        val infos = pm.queryIntentActivities(intent, 0)
        for (info in infos) {
            if (info.activityInfo.packageName == packageName) {
                return info.activityInfo.name
            }
        }
        return "no $packageName"
    }

    @JvmStatic
    @JvmOverloads
    fun finishActivity(mContext: Context, isTransition: Boolean = false) {
        removeActivity((mContext as Activity))
        if (isTransition) {
            mContext.onBackPressed()
        } else {
            mContext.finish()
        }
    }


}