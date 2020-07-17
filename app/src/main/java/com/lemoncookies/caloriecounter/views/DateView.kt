package com.lemoncookies.caloriecounter.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.lemoncookies.caloriecounter.R

class DateView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val bgLayout: LinearLayout
    private val tvTitle: TextView
    private val tvDate: TextView

    private val selectedBgColor: Int
    private val unselectedBgColor: Int
    private val selectedTextColor: Int
    private val unselectedTextColor: Int

    private var mSelected: Boolean = false

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_date, this, true)

        bgLayout = view.findViewById(R.id.layout)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDate = view.findViewById(R.id.tvDate)

        val styleAttrs = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DateView,
            0, 0
        )

        selectedBgColor =
            styleAttrs.getColorStateList(R.styleable.DateView_selectedBackgroundColor)?.defaultColor
                ?: Color.RED
        unselectedBgColor =
            styleAttrs.getColorStateList(R.styleable.DateView_unselectedBackgroundColor)?.defaultColor
                ?: Color.GRAY
        selectedTextColor =
            styleAttrs.getColorStateList(R.styleable.DateView_selectedTextColor)?.defaultColor
                ?: Color.WHITE
        unselectedTextColor =
            styleAttrs.getColorStateList(R.styleable.DateView_unselectedTextColor)?.defaultColor
                ?: Color.BLACK

        deselect()
    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

    fun setDateText(date: String) {
        tvDate.text = date
    }

    fun select() {
        mSelected = true
        bgLayout.setBackgroundColor(selectedBgColor)
        tvTitle.setTextColor(selectedTextColor)
        tvDate.setTextColor(selectedTextColor)
    }

    fun deselect() {
        mSelected = false
        bgLayout.setBackgroundColor(unselectedBgColor)
        tvTitle.setTextColor(unselectedTextColor)
        tvDate.setTextColor(unselectedTextColor)
    }
}