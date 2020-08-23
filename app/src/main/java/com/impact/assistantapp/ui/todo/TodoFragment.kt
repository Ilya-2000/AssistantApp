package com.impact.assistantapp.ui.todo



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.impact.assistantapp.R


class TodoFragment : Fragment() {

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = findNavController()
        todoViewModel =
                ViewModelProviders.of(this).get(TodoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_todo, container, false)
        val floatingActionButton =
            requireActivity().findViewById<View>(R.id.fab) as FloatingActionButton
        floatingActionButton.show()
        recyclerView = root.findViewById(R.id.todo_rv)

        floatingActionButton.setOnClickListener {
            val dialog = NewTodoDialog()
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            dialog.show(ft, "newTodo")
        }

        return root
    }
}