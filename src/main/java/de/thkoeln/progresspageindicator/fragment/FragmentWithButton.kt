package de.thkoeln.progresspageindicator.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.thkoeln.progresspageindicator.R
import de.thkoeln.progresspageindicator.extended.ConditionalProgressIndicator
import kotlinx.android.synthetic.main.fragment2.*

class FragmentWithButton : Fragment() {

    companion object {
        private const val KEY = "key2"
        fun newInstance(count: Int): FragmentWithButton {
            val fragment = FragmentWithButton()
            val args = Bundle()
            args.putInt(KEY, count)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    //(TODO: for later sample make less dependent, currently very flaky)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragmentTextView2.text = "${fragmentTextView2.text} ${arguments?.getInt(KEY)}"

        fragmentButton.setOnClickListener {
            //getactivity / pager
            val viewPager = activity?.findViewById<ViewPager>(R.id.da_pager2)
            val indicator = activity?.findViewById<ConditionalProgressIndicator>(R.id.indicator2)

            viewPager?.let {
                indicator?.setConditionSatisfied(it.currentItem)
            }
        }
    }
}
