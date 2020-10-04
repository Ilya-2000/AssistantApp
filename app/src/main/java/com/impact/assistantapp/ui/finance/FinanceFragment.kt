package com.impact.assistantapp.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
        val cardRv = root.findViewById<RecyclerView>(R.id.cards__finance_rv)
        val expenseRv = root.findViewById<RecyclerView>(R.id.expenses_finance_rv)
        val addBudgetBtn = root.findViewById<FloatingActionButton>(R.id.add_budget_finance_fab)
        val deleteBudgetBtn = root.findViewById<FloatingActionButton>(R.id.delete_budget_finance_fab)

        return root
    }
}