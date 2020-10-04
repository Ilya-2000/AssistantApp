package com.impact.assistantapp.ui.todo



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.impact.assistantapp.R
import com.impact.assistantapp.adapters.TodoRvAdapter
import com.impact.assistantapp.data.model.Plan
import kotlinx.android.synthetic.main.app_bar_main.*


class TodoFragment : Fragment() {
    private val TAG = "TodoFragment"
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = findNavController()
        todoViewModel =
                ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_todo, container, false)
        val floatingActionButton =
            requireActivity().findViewById<View>(R.id.fab) as FloatingActionButton
        floatingActionButton.show()
        recyclerView = root.findViewById(R.id.todo_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        todoViewModel.planList.observe(viewLifecycleOwner, Observer {
            val todoAdapter = TodoRvAdapter(todoViewModel, viewLifecycleOwner)
            recyclerView.adapter = todoAdapter
            Log.d(TAG, "PlanList, ${it.size}")
            todoAdapter.notifyDataSetChanged()
        })

        floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_nav_todo_to_newTodoFragment)
        }


        return root
    }


    override fun onResume() {
        super.onResume()

        val floatingActionButton =
            requireActivity().findViewById<View>(R.id.fab) as FloatingActionButton
        floatingActionButton.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val floatingActionButton =
            requireActivity().findViewById<View>(R.id.fab) as FloatingActionButton
        floatingActionButton.hide()
    }

}