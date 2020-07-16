package com.lemoncookies.caloriecounter.ui.tabCalories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord

class CalorieListAdapter(items: List<CalorieRecord>) :
    RecyclerView.Adapter<CalorieListAdapter.CalorieItem>() {
    private val items: MutableList<CalorieRecord> = items.toMutableList()

    inner class CalorieItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun init(item: CalorieRecord) {
            itemView.findViewById<TextView>(R.id.tvName).text = item.name
            itemView.findViewById<TextView>(R.id.tvSum).text = item.calories.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalorieItem {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_calorie_record, parent, false)
        return CalorieItem(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CalorieItem, position: Int) {
        holder.init(items[position])
    }
}