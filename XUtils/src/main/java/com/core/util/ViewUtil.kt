package com.core.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes

/**
 * 设置[View]的圆角
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [corner] 圆角值
 */
fun View.setCorner(corner: Float) {
    when (background) {
        is GradientDrawable -> {
            (background as GradientDrawable)?.cornerRadius = corner
        }
        is ColorDrawable -> {
            val drawable = GradientDrawable().apply {
                cornerRadius = corner
                setColor((background as ColorDrawable).color)
            }
            setDrawable(this, drawable)
        }
        else -> {
            val drawable = GradientDrawable().apply {
                cornerRadius = corner
                setColor(Color.TRANSPARENT)
            }
            setDrawable(this, drawable)
        }
    }
}

/**
 * 设置[View]的圆角
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [cornerArr] 圆角值
 */
fun View.setCorner(cornerArr: FloatArray) {
    when (background) {
        is GradientDrawable -> {
            (background as GradientDrawable)?.cornerRadii = cornerArr
        }
        is ColorDrawable -> {
            val drawable = GradientDrawable().apply {
                cornerRadii = cornerArr
                setColor((background as ColorDrawable).color)
            }
            setDrawable(this, drawable)
        }
        else -> {
            val drawable = GradientDrawable().apply {
                cornerRadii = cornerArr
                setColor(Color.TRANSPARENT)
            }
            setDrawable(this, drawable)
        }
    }
}

/**
 * 设置[View]的背景颜色
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [color] 资源颜色
 */
fun View.setBackground(@ColorRes color: Int) {
    setBackgroundColor(ResourceUtil.getColor(color))
}

/**
 * 设置[View]的背景颜色
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [startColor] 开始颜色
 * @param [endColor] 结束颜色
 * @param [orientation] 渐变方向
 */
@JvmOverloads
fun View.setBackground(
    @ColorRes startColor: Int,
    @ColorRes endColor: Int,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
) {
    setDrawable(
        this,
        GradientDrawable(
            orientation,
            intArrayOf(ResourceUtil.getColor(startColor), ResourceUtil.getColor(endColor))
        )
    )
}


/**
 * 设置[View]的背景颜色及圆角
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [corner] 圆角
 * @param [startColor] 开始颜色
 * @param [endColor] 结束颜色
 * @param [orientation] 渐变方向
 */
@JvmOverloads
fun View.setBackground(
    corner: Float,
    @ColorRes startColor: Int,
    @ColorRes endColor: Int,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
) {
    setDrawable(
        this,
        GradientDrawable(
            orientation,
            intArrayOf(ResourceUtil.getColor(startColor), ResourceUtil.getColor(endColor))
        ).apply {
            cornerRadius = corner
        }
    )
}

/**
 * 设置[View]的背景颜色及圆角
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [corner] 圆角
 * @param [color] 开始颜色
 */
fun View.setBackground(
    corner: Float,
    @ColorRes color: Int
) {
    setDrawable(
        this,
        GradientDrawable().apply {
            cornerRadius = corner
            setColor(ResourceUtil.getColor(color))
        }
    )
}

/**
 * 设置[View]的背景颜色及圆角
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [corner] 圆角
 * @param [color] 开始颜色
 */
fun View.setBackground(
    corner: FloatArray,
    @ColorRes color: Int
) {
    setDrawable(
        this,
        GradientDrawable().apply {
            cornerRadii = corner
            setColor(ResourceUtil.getColor(color))
        }
    )
}

/**
 * 设置[View]的边框
 * @author like
 * @date 6/22/21 2:50 PM
 * @param [strikeWidth] 边框宽度
 * @param [strikeColor] 边框颜色
 */
fun View.setStroke(strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this, GradientDrawable().apply {
        setStroke(strikeWidth, ResourceUtil.getColor(strikeColor))
    })
}


/**
 * 设置[View]的圆角和边框
 * @param [corner] 圆角
 * @param [strikeWidth] 边框大小
 * @param [strikeColor] 边框颜色
 */
fun View.setCornerAndStroke(corner: Float, strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this,GradientDrawable().apply {
        cornerRadius = corner
        setStroke(strikeWidth,ResourceUtil.getColor(strikeColor))
    })
}

/**
 * 设置[View]的圆角和边框
 * @param [corner] 圆角
 * @param [strikeWidth] 边框大小
 * @param [strikeColor] 边框颜色（未处理）
 */
fun View.setCornerAndStroke(corner: FloatArray, strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this,GradientDrawable().apply {
        cornerRadii = corner
        setStroke(strikeWidth,ResourceUtil.getColor(strikeColor))
    })
}

/**
 * 设置背景和边框
 * @param [color] 背景颜色（未处理）
 * @param [strikeWidth] 边框大小
 * @param [strikeColor] 边框颜色（未处理）
 */
fun View.setBackgroundAndStroke(@ColorRes color: Int, strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this,GradientDrawable().apply {
        setColor(ResourceUtil.getColor(color))
        setStroke(strikeWidth,ResourceUtil.getColor(strikeColor))
    })
}

/**
 * 设置视图的圆角、背景、边框
 * @param [corner] 圆角
 * @param [color] 背景颜色（未处理）
 * @param [strikeWidth] 边框大小
 * @param [strikeColor] 边框颜色（未处理）
 */
fun View.setCornerAndBackgroundAndStroke(corner: Float, @ColorRes color: Int, strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this,GradientDrawable().apply {
        setColor(ResourceUtil.getColor(color))
        setStroke(strikeWidth,ResourceUtil.getColor(strikeColor))
        cornerRadius = corner
    })
}

/**
 * 设置视图的圆角、背景、边框
 * @param [corner] 圆角
 * @param [color] 背景颜色（未处理）
 * @param [strikeWidth] 边框大小
 * @param [strikeColor] 边框颜色（未处理）
 */
fun View.setCornerAndBackgroundAndStroke(corner: FloatArray, @ColorRes color: Int, strikeWidth: Int, @ColorRes strikeColor: Int) {
    setDrawable(this,GradientDrawable().apply {
        setColor(ResourceUtil.getColor(color))
        setStroke(strikeWidth,ResourceUtil.getColor(strikeColor))
        cornerRadii = corner
    })
}

private fun setDrawable(view: View, drawable: Drawable) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
        view.background = drawable
    } else {
        view.setBackgroundDrawable(drawable)
    }
}