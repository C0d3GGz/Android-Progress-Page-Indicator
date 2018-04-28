package de.thkoeln.progresspageindicator

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        da_pager2.adapter = SampleFragmentAdapter2(supportFragmentManager)
    }
}

class SampleFragmentAdapter2(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments : MutableList<FragmentWithButton> = mutableListOf()

    init {
        for(i in 1..3)
            fragments.add(FragmentWithButton.newInstance(i))
    }

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
