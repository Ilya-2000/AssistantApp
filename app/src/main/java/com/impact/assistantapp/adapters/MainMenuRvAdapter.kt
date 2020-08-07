package com.impact.assistantapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.squareup.picasso.Picasso

class MainMenuRvAdapter(var navController: NavController) : RecyclerView.Adapter<MainMenuRvAdapter.ViewHolder>() {
    private val dataList = mutableListOf<MenuMain>(
        MenuMain("Список дел", R.drawable.ic_baseline_list_alt_24),
        MenuMain("Финансы", R.drawable.ic_baseline_attach_money_24)
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_main_item, parent, false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.itemView.setOnClickListener {
            //navController.navigate()
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<TextView>(R.id.menu_main_item_text)
        private val img = itemView.findViewById<ImageView>(R.id.menu_main_item_img)

        fun bind(item: MenuMain) {
            text.text = item.name
            Picasso.get()
                .load(item.img)
                .into(img)

        }
    }
}

data class MenuMain(
    val name: String,
    val img: Int
)
