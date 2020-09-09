package com.impact.assistantapp.ui.todo

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.Plan

class TodoViewModel : ViewModel() {

    private val TAG = "TodoViewModel"

    var name: String = ""
    var description: String = ""
    var time: String = ""
    var date: String = ""


    private val _planList = MutableLiveData<MutableList<Plan>>()
    val planList: LiveData<MutableList<Plan>>
        get() = _planList

    private val _plan = MutableLiveData<Plan>()
    val plan: LiveData<Plan>
        get() = _plan

    private val _newPlan = MutableLiveData<Plan>()
    val newPlan: LiveData<Plan>
        get() = _newPlan

    fun setPlan(data: Plan) {
        _plan.value = data
        addToPlanList(data)
        Log.d(TAG, "setPlan, $data")
    }

    fun setPlanList(data: MutableList<Plan>) {
        _planList.value = data
    }

    fun setNewPlan(data: Plan) {

    }

    fun addToPlanList(data: Plan) {
        _planList.value?.add(data)
        Log.d(TAG, "addToPlanList, $data")
    }


}