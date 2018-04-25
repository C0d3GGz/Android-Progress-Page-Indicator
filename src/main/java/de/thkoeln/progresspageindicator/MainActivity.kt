package de.thkoeln.progresspageindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressIndicator = ProgressIndicator(this, 4)
        val view = findViewById<ViewGroup>(android.R.id.content)

        view.addView(progressIndicator)
        progressIndicator.setActive(0)
        progressIndicator.setVisited(3)
    }
}
