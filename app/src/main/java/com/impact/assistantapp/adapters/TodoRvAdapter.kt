package com.impact.assistantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.databinding.TodoBinding

import com.impact.assistantapp.ui.todo.TodoViewModel

class TodoRvAdapter (private val todoViewModel: TodoViewModel) : RecyclerView.Adapter<TodoRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRvAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: TodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }




    override fun onBindViewHolder(holder: TodoRvAdapter.ViewHolder, position: Int) {
        val item = todoViewModel
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return todoViewModel.planSize
    }

    inner class ViewHolder(val todoBinding: TodoBinding): RecyclerView.ViewHolder(todoBinding.root) {

        fun bind(item: TodoViewModel) {
            this.todoBinding.viewModel = item
            todoBinding.executePendingBindings()
        }
    }

}