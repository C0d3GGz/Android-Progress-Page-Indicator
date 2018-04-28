package de.thkoeln.progresspageindicator.extended

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import de.thkoeln.progresspageindicator.CircleIndicator
import de.thkoeln.progresspageindicator.ProgressIndicator

class ConditionalProgressIndicator(con: Context, attrs: AttributeSet)
    : ProgressIndicator(con, attrs){

    private lateinit var conditionList : MutableList<Boolean>

    override fun initialize(){
        super.initialize()

        conditionList = MutableList(circles.size){ false }
        circles.forEach {
            it.visitedColor = ContextCompat.getColor(context, android.R.color.darker_gray)
        }
        circles[0].setVisited()
    }

    fun setConditionSatisfied(position: Int){

        val visitedColor = ContextCompat.getColor(context, CircleIndicator.DEFAULT_MAIN_COLOR_RES)
        circles[position].visitedColor = visitedColor
        circles[position].setVisited()
        circles[position].invalidate()
        conditionList[position] = true

    }
}