package de.thkoeln.progresspageindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        da_pager.adapter = SampleFragmentAdapter(supportFragmentManager)
    }
}

class SampleFragmentAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragments : MutableList<JustAFragment> = mutableListOf()

    init {
        fragments.add(JustAFragment.newInstance(1))
        fragments.add(JustAFragment.newInstance(2))
        fragments.add(JustAFragment.newInstance(3))
    }

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
