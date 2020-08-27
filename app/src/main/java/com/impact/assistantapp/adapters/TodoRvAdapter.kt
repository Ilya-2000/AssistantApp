package com.impact.assistantapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.Plan
import com.impact.assistantapp.databinding.TodoBinding

import com.impact.assistantapp.ui.todo.TodoViewModel

class TodoRvAdapter (private val todoViewModel: TodoViewModel) : RecyclerView.Adapter<TodoRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRvAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: TodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }




    override fun onBindViewHolder(holder: TodoRvAdapter.ViewHolder, position: Int) {
        val item = todoViewModel.planList.value?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        Log.d("getItemCount", todoViewModel.planList.value?.size.toString())
        return todoViewModel.planList.value?.size!!
    }

    inner class ViewHolder(val todoBinding: TodoBinding): RecyclerView.ViewHolder(todoBinding.root) {

        fun bind(item: Plan) {
            this.todoBinding.plan = item
            todoBinding.executePendingBindings()

            Log.d("TodoRv", item.name)
        }
    }

}