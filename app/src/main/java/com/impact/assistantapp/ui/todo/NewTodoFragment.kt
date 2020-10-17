package com.impact.assistantapp.ui.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.impact.assistantapp.R
import com.impact.assistantapp.databinding.FragmentNewTodoBinding
import kotlinx.android.synthetic.main.fragment_new_todo.*
import java.text.SimpleDateFormat
import java.util.*

class NewTodoFragment : Fragment() {


    private lateinit var todoViewModel: TodoViewModel
    private val TAG = "NewTodoFragment"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewTodoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_todo, container, false)
        val view = binding.root
        var str = "null"
        todoViewModel = ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
        binding.viewModel = todoViewModel
        binding.lifecycleOwner = this
        val navController = findNavController()
        //todoViewModel.setNavigation(navController)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.dateAddPlanBtn.setOnClickListener {



            val dpd = DatePickerDialog(requireContext(), { _view, _year, _monthOfYear, _dayOfMonth ->

                //todoViewModel.plan.value?.date = "$_dayOfMonth $_monthOfYear, $_year"
                str = "$_dayOfMonth ${_monthOfYear + 1}, $_year"
                todoViewModel.setSelectDate(str)


            }, year, month, day)

            dpd.show()


            //todoViewModel.showDatePickerDialog()
        }

        binding.timeAddPlanBtn.setOnClickListener {
            Log.d(TAG, "STR =  $str")
            todoViewModel.selectDate.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, it)
            })


            val timeListener = TimePickerDialog.OnTimeSetListener { _timePicker, _hour, _minute ->
                c.set(Calendar.HOUR_OF_DAY, _hour)
                c.set(Calendar.MINUTE, _minute)
                val time = SimpleDateFormat("HH:mm").format(c.time)
                todoViewModel.setSelectTime(time)
                Log.d(TAG, "TIME =  $time")
            }
            TimePickerDialog(requireContext(), timeListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        binding.acceptNewTodoFab.setOnClickListener {
            todoViewModel.setPlanName(name_add_plan.text.toString())
            todoViewModel.setDescription(description_add_plan.text.toString())
            todoViewModel.acceptPlan()
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            navController.popBackStack()

        }





        return view
    }


    override fun onStart() {
        super.onStart()
        todoViewModel.plan.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.date)
        })
    }



}