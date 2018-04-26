package de.thkoeln.progresspageindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        da_pager.adapter = SampleFragmentAdapter(supportFragmentManager)
    }
}

class SampleFragmentAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments : MutableList<JustAFragment> = mutableListOf()

    init {
        for(i in 1..3)
            fragments.add(JustAFragment.newInstance(i))
    }

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
