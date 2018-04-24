package de.thkoeln.progresspageindicator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.circle_indicator.view.*

class CircleIndicator(con : Context, attrs: AttributeSet?) : ConstraintLayout(con, attrs){

    constructor(con: Context) : this(con, null)

    private var circleSizeInPixel = 0
    private var innerStrokeSizeInPixel = 0
    private var outerStrokeSizeInPixel = 0

    init{
        LayoutInflater.from(con).inflate(R.layout.circle_indicator, this)

        attrs?.let {
            val attributes = con.theme.obtainStyledAttributes(attrs, R.styleable.CircleIndicator,
                    0 ,0)

            circleSizeInPixel = attributes.getDimensionPixelSize(
                    R.styleable.CircleIndicator_circle_size, circleSizeInPixel)

            innerStrokeSizeInPixel = attributes.getDimensionPixelSize(
                    R.styleable.CircleIndicator_inner_stroke_size, innerStrokeSizeInPixel)

            outerStrokeSizeInPixel  = attributes.getDimensionPixelSize(
                    R.styleable.CircleIndicator_outer_stroke_size, outerStrokeSizeInPixel )
        }

        val outerStrokeCircle = ShapeDrawable(OvalShape())
        val innerStrokeCircle = ShapeDrawable(OvalShape())

        if(outerStrokeSizeInPixel == 0){
            outer_circle_stroke.background = null
        }
        else{
            outerStrokeCircle.paint.color = ContextCompat.getColor(con, R.color.colorAccent)
            outerStrokeCircle.intrinsicHeight = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outerStrokeCircle.intrinsicWidth = circleSizeInPixel + (outerStrokeSizeInPixel*2)
            outer_circle_stroke.background = outerStrokeCircle
        }

        if(innerStrokeSizeInPixel == 0){
            inner_circle_stroke.background = null
        }
        else{
            innerStrokeCircle.paint.color = Color.GREEN
            innerStrokeCircle.intrinsicHeight = circleSizeInPixel
            innerStrokeCircle.intrinsicWidth = circleSizeInPixel
            inner_circle_stroke.background = innerStrokeCircle
        }


        val mainCircle = ShapeDrawable(OvalShape())
        mainCircle.intrinsicWidth = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        mainCircle.intrinsicHeight = circleSizeInPixel - (innerStrokeSizeInPixel*2)
        mainCircle.paint.color = ContextCompat.getColor(con, R.color.colorPrimary)
        mainCircle.paint.strokeWidth = innerStrokeSizeInPixel.toFloat()
        main_circle.background = mainCircle

    }
}