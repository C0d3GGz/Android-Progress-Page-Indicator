package de.thkoeln.progresspageindicator

import android.util.DisplayMetrics
import android.util.TypedValue

object DimensionHelper{
    fun getRoundedPixel(dm: DisplayMetrics, dp: Float) =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm).toInt()

    fun getDp(dm: DisplayMetrics, pixel: Int) =
            pixel.toFloat() / (dm.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}