package com.impact.assistantapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.Plan
import com.impact.assistantapp.databinding.TodoBinding

import com.impact.assistantapp.ui.todo.TodoViewModel

class TodoRvAdapter (private val todoViewModel: TodoViewModel, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<TodoRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRvAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: TodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.todo_item, parent, false)

        return ViewHolder(view)
    }




    override fun onBindViewHolder(holder: TodoRvAdapter.ViewHolder, position: Int) {
        var planList = MutableLiveData<MutableList<Plan>>()
        todoViewModel.planList.observe(lifecycleOwner, Observer {
            if (it != null) {
                planList.postValue(it)
                planList.value?.get(position)?.let { it1 -> holder.bind(it1) }
                Log.d("onBindViewHolder", it[position].toString())
            }
        })
        notifyDataSetChanged()

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
            notifyDataSetChanged()
        }
    }

}