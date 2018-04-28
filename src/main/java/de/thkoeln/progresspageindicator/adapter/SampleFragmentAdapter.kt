package de.thkoeln.progresspageindicator.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import de.thkoeln.progresspageindicator.fragment.FragmentWithButton
import de.thkoeln.progresspageindicator.fragment.JustAFragment

class SampleFragmentAdapter(buttonFragment: Boolean, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    private val fragments : MutableList<Fragment> = mutableListOf()

    init {
        for(i in 1..3)
            if(buttonFragment){
                fragments.add(FragmentWithButton.newInstance(i))
            }
            else{
                fragments.add(JustAFragment.newInstance(i))
            }
    }

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
