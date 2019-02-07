package sa.com.etucook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sa.com.etucook.R

class MealFragment: Fragment() {

    companion object {
        fun newInstance() = MealFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meal_fragment, container, false)
    }
}