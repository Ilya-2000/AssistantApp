package com.impact.assistantapp.ui.todo

import android.app.Application
import android.app.DatePickerDialog
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.Plan
import java.util.*

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "TodoViewModel"


    private val _planList = MutableLiveData<MutableList<Plan>>()
    val planList: LiveData<MutableList<Plan>>
        get() = _planList

    private val _plan = MutableLiveData<Plan>()
    val plan: LiveData<Plan>
        get() = _plan

    private val _selectDate = MutableLiveData<String>()
    val selectDate: LiveData<String>
        get() = _selectDate

    private val _selectTime = MutableLiveData<String>()
    val selectTime: LiveData<String>
        get() = _selectTime

    private val _planName = MutableLiveData<String>()
    val planName: LiveData<String>
        get() = _planName

    private val _description = MutableLiveData<String>()
    val descriptionPlan: LiveData<String>
        get() = _description





    fun setPlan(data: Plan) {
        _plan.value = data
        addToPlanList(data)
        Log.d(TAG, "setPlan, $data")
    }

    fun setSelectDate(data: String) {
        _selectDate.postValue(data)

    }

    fun setSelectTime(data: String) {
        _selectTime.postValue(data)

    }

    fun setPlanName(data: String) {
        _planName.value = data

    }

    fun setDescription(data: String) {
        _description.value = data

    }

    fun setPlanList(data: MutableList<Plan>) {
        _planList.value = data
    }

    fun addToPlanList(data: Plan) {
        _planList.value?.add(data)
        Log.d(TAG, "addToPlanList, $data")
    }


    fun acceptPlan() {

        if (planName.value != null) {
            val plan = Plan(
                planName.value.toString(),
                descriptionPlan.value.toString(),
                selectTime.value.toString(),
                selectDate.value.toString(),
                false)
            _plan.postValue(plan)
            Log.d(TAG, "Plan LiveData ${_plan.value}")
        }


    }


    /*fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var str = "null"

        val dpd = DatePickerDialog(getApplication(), { _view, _year, _monthOfYear, _dayOfMonth ->

            //todoViewModel.plan.value?.date = "$_dayOfMonth $_monthOfYear, $_year"
            str = "$_dayOfMonth $_monthOfYear, $_year"
            _selectDate.value = str

        }, year, month, day)

        dpd.show()
    }*/


}