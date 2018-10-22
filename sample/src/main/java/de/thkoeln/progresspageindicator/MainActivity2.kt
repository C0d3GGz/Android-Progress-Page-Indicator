package de.thkoeln.progresspageindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.thkoeln.progresspageindicator.adapter.SampleFragmentAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        da_pager2.adapter = SampleFragmentAdapter(true, supportFragmentManager)
    }
}
