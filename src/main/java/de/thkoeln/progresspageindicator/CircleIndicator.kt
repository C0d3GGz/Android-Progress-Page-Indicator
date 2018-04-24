package de.thkoeln.progresspageindicator

import android.content.Context
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

    constructor(con: Context) : this(con, null)

    private val mainCircle = ShapeDrawable(OvalShape())
    private val outerStrokeCircle = ShapeDrawable(OvalShape())
    private val innerStrokeCircle = ShapeDrawable(OvalShape())

    //todo: public
    private var circleSizeInPixel = 0
    private var innerStrokeSizeInPixel = 0
    private var outerStrokeSizeInPixel = 0

    private val defaultInnerStrokeColorRes = R.color.colorPrimary
    private val defaultOuterStrokeColorRes = R.color.colorAccent

    var fillColor = getBackgroundColor()
        set(value) {
            field = value
            mainCircle.paint.color = value!!
        }

    var innerStrokeColor = ContextCompat.getColor(con, defaultInnerStrokeColorRes)
        set(value) {
            field = value
            innerStrokeCircle.paint.color = value
        }

    var outerStrokeColor = ContextCompat.getColor(con, defaultOuterStrokeColorRes)
        set(value) {
            field = value
            outerStrokeCircle.paint.color = value
        }

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

            fillColor = a.getColor(CircleIndicator_fill_color, fillColor!!)
            innerStrokeColor = a.getColor(CircleIndicator_inner_stroke_color, innerStrokeColor)
            outerStrokeColor = a.getColor(CircleIndicator_outer_stroke_color, outerStrokeColor)
        }

        if(outerStrokeSizeInPixel == 0){
            outer_circle_stroke.background = null
        }
        else{
            outerStrokeCircle.intrinsicHeight = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outerStrokeCircle.intrinsicWidth = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outer_circle_stroke.background = outerStrokeCircle
        }

        if(innerStrokeSizeInPixel == 0){
            inner_circle_stroke.background = null
        }
        else{
            innerStrokeCircle.intrinsicHeight = circleSizeInPixel
            innerStrokeCircle.intrinsicWidth = circleSizeInPixel
            inner_circle_stroke.background = innerStrokeCircle
        }

        mainCircle.intrinsicWidth = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        mainCircle.intrinsicHeight = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        mainCircle.paint.strokeWidth = innerStrokeSizeInPixel.toFloat()
        main_circle.background = mainCircle

    }

    private fun getBackgroundColor() : Int? {
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

}