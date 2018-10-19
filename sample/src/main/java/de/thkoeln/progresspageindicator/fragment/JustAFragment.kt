package de.thkoeln.progresspageindicator.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.thkoeln.progresspageindicator.R
import kotlinx.android.synthetic.main.fragment.*

open class JustAFragment : Fragment(){

    companion object {
        private const val KEY = "key"
        fun newInstance(count: Int): JustAFragment{
            val fragment = JustAFragment()
            val args = Bundle()
            args.putInt(KEY, count)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentIndex = arguments?.getInt(KEY)
        fragmentTextView.text = "${fragmentTextView.text } $fragmentIndex"
    }
}