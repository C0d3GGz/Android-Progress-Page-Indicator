package de.thkoeln.progresspageindicator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import de.thkoeln.progresspageindicator.R.styleable.*
import kotlinx.android.synthetic.main.circle_indicator.view.*
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue

class CircleIndicator(con : Context, attrs: AttributeSet?) : ConstraintLayout(con, attrs){

    companion object {
        private const val DEFAULT_INNER_STROKE_COLOR_RES = android.R.color.darker_gray
        private const val DEFAULT_OUTER_STROKE_COLOR_RES = R.color.colorAccent
        private const val DEFAULT_MAIN_COLOR_RES = android.R.color.holo_green_light //TODO
        private const val DEFAULT_CIRCLE_DP = 14
        private const val DEFAULT_STROKE_SIZE_DP = 2
    }

    constructor(con: Context) : this(con, null)

    private val mainCircle = ShapeDrawable(OvalShape())
    private val outerStrokeCircle = ShapeDrawable(OvalShape())
    private val innerStrokeCircle = ShapeDrawable(OvalShape())

    var visitedColor = ContextCompat.getColor(con, DEFAULT_MAIN_COLOR_RES)
    var unvisitedColor = ContextCompat.getColor(con, DEFAULT_INNER_STROKE_COLOR_RES)
    var activeColor = ContextCompat.getColor(con, DEFAULT_OUTER_STROKE_COLOR_RES)

    var circleSizeInDp : Int = DEFAULT_CIRCLE_DP
        set(value) { field = value; circleSizeInPixel = getRoundedPixel(value) }

    var innerStrokeSizeInDp : Int = DEFAULT_STROKE_SIZE_DP
        set(value) { field= value; innerStrokeSizeInPixel = getRoundedPixel(value)}

    var outerStrokeSizeInDp : Int = DEFAULT_STROKE_SIZE_DP
        set(value) { field = value; outerStrokeSizeInPixel = getRoundedPixel(value) }

    private var circleSizeInPixel = -1
        set(value) { field = value; updateMainCircle() }

    private var innerStrokeSizeInPixel = -1
        set(value) { field = value; updateInnerCircle() }

    private var outerStrokeSizeInPixel = 0
        set(value) { field = value; updateOuterCircle() }

    init{
        LayoutInflater.from(con).inflate(R.layout.circle_indicator, this)

        attrs?.let {
            val a = con.theme.obtainStyledAttributes(attrs, R.styleable.CircleIndicator,
                    0 ,0)

            circleSizeInPixel = a.getDimensionPixelSize(
                    CircleIndicator_circle_size, circleSizeInPixel)

            innerStrokeSizeInPixel = a.getDimensionPixelSize(
                    CircleIndicator_inner_stroke_size, innerStrokeSizeInPixel)

            outerStrokeSizeInPixel  = a.getDimensionPixelSize(
                    CircleIndicator_outer_stroke_size, outerStrokeSizeInPixel )

            visitedColor = a.getColor(CircleIndicator_visited_color, visitedColor)
            unvisitedColor = a.getColor(CircleIndicator_unvisited_color, unvisitedColor)
            activeColor = a.getColor(CircleIndicator_active_color, activeColor)
        }

        setVisited()
    }

    private fun getBackgroundColor() : Int? {
        //https://stackoverflow.com/a/14468034/3694345
        val a = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, a, true)
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            // windowBackground is a color
            return a.data
        } else {
            // windowBackground is not a color, probably a drawable
            val drawable = ContextCompat.getDrawable(context, a.resourceId)
            if(drawable is ColorDrawable) return drawable.color }
        return null
    }

    private fun updateMainCircle(){
        mainCircle.intrinsicWidth = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        mainCircle.intrinsicHeight = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        main_circle.background = mainCircle
    }

    private fun updateInnerCircle() {
        if(innerStrokeSizeInPixel == 0){
            inner_circle_stroke.background = null
        }
        else{
            innerStrokeCircle.intrinsicHeight = circleSizeInPixel
            innerStrokeCircle.intrinsicWidth = circleSizeInPixel
            inner_circle_stroke.background = innerStrokeCircle
        }
        updateMainCircle() //inner circle affects main circle
    }

    private fun updateOuterCircle(){
        if(outerStrokeSizeInPixel == 0){
            outer_circle_stroke.background = null
        }
        else{
            outerStrokeCircle.intrinsicHeight = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outerStrokeCircle.intrinsicWidth = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outer_circle_stroke.background = outerStrokeCircle
        }
    }

    fun setVisited(){
        //fill green
        innerStrokeSizeInPixel = 0
        mainCircle.paint.color = visitedColor
    }

    fun setUnvisited(){
        //do not fill + grey stroke
        innerStrokeSizeInPixel = getRoundedPixel(DEFAULT_STROKE_SIZE_DP)
        mainCircle.paint.color = getBackgroundColor() ?: Color.WHITE
        innerStrokeCircle.paint.color = unvisitedColor

    }
    //todo: setActive / setInactive - advanced indication

    private fun getRoundedPixel(dp: Int) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(), resources.displayMetrics).toInt()
    }
}