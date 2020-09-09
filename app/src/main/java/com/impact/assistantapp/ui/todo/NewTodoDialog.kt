package com.impact.assistantapp.ui.todo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.Plan
import com.impact.assistantapp.databinding.NewTodoItemBinding


class NewTodoDialog() : DialogFragment() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    /*private lateinit var nameText: TextInputEditText
    private lateinit var descriptionText: EditText*/
    private lateinit var viewModel: TodoViewModel
    //private lateinit var plan: Plan

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NewTodoItemBinding = DataBindingUtil.inflate(inflater, R.layout.new_todo_item, container, false)
        //val root = inflater.inflate(R.layout.new_todo_item, container, false)
        val root = binding.root
        viewModel =
            ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
        toolbar = root.findViewById(R.id.new_todo_toolbar)
        /*nameText = root.findViewById(R.id.name_new_todo_text)
        descriptionText = root.findViewById(R.id.description_new_todo_text)*/


        toolbar.setNavigationOnClickListener {
            dismiss()
        }
        toolbar.inflateMenu(R.menu.add_menu)


        binding.saveNewTodoButton.setOnClickListener {
            val plan = Plan(
                "",
                binding.nameNewTodoText.text.toString(),
                binding.descriptionNewTodoText.text.toString(),
                "",
                "",
                false
            )
            viewModel.addToPlanList(plan)


            dismiss()

        }




        return root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_item_menu) {
            dismiss()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }

    }
}