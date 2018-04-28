package de.thkoeln.progresspageindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.thkoeln.progresspageindicator.adapter.SampleFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        da_pager.adapter = SampleFragmentAdapter(false, supportFragmentManager)
    }
}