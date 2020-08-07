package com.impact.assistantapp.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.impact.assistantapp.R

class FinanceFragment : Fragment() {

    private lateinit var financeViewModel: FinanceViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        financeViewModel =
                ViewModelProviders.of(this).get(FinanceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_finance, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        financeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}