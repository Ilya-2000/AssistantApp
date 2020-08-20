package com.impact.assistantapp.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.Plan

class TodoViewModel : ViewModel() {

    private val TAG = "TodoViewModel"
    var planSize: Int = 0

    private val _planList = MutableLiveData<MutableList<Plan>>()
    val planList: LiveData<MutableList<Plan>>
        get() = _planList


}