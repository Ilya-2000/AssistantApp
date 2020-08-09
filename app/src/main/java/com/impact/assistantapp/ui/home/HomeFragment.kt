package com.impact.assistantapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.adapters.MainMenuRvAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val menuRv = root.findViewById<RecyclerView>(R.id.menu_main_rv)
        val temperature = root.findViewById<TextView>(R.id.temperature_card_text)
        menuRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val navController = findNavController()
        val menuListAdapter = MainMenuRvAdapter(navController)
        menuRv.adapter = menuListAdapter
        menuListAdapter.notifyDataSetChanged()
        homeViewModel.getCurrentWeather()
        homeViewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            val a = it.temp - 273
            temperature.text = a.toString()

        })

        return root
    }
}