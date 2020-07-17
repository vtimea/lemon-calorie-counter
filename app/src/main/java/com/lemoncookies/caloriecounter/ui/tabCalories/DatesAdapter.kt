package com.lemoncookies.caloriecounter.ui.tabCalories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.views.DateView
import org.joda.time.DateTime

abstract class DatesAdapter(private val dates: List<DateTime>, selectedPos: Int) :
    DatePicker,
    RecyclerView.Adapter<DatesAdapter.DateHolder>() {
    private var selectedDate: DateTime

    init {
        selectedDate = if (selectedPos >= dates.size) {
            dates[0]
        } else {
            dates[selectedPos]
        }
    }

    inner class DateHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(date: DateTime) {
            val dateView = itemView.findViewById<DateView>(R.id.dateView)
            dateView.setTitle(date.dayOfWeek().asShortText) //todo
            dateView.setDateText(date.dayOfMonth().asText)
            if (selectedDate == date) {
                dateView.select()
            } else {
                dateView.deselect()
            }
            dateView.setOnClickListener {
                if (date == selectedDate) return@setOnClickListener
                onDateSelected(date)
                val indexOfLast = dates.indexOf(selectedDate)
                val indexCurrent = dates.indexOf(date)
                selectedDate = date
                if (indexOfLast > 0) {
                    notifyItemChanged(indexOfLast)
                }
                if (indexCurrent > 0) {
                    notifyItemChanged(indexCurrent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val holder = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
        return DateHolder(holder)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        holder.bind(dates[position])
    }
}