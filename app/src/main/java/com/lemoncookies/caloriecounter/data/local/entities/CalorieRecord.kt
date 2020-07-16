package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_ID
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_NAME
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_VALUE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_CALORIES

@Entity(tableName = TABLE_CALORIES)
data class CalorieRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CALORIES_ID)
    var id: Long = 0,

    @ColumnInfo(name = CALORIES_NAME)
    var name: String = "",

    @ColumnInfo(name = CALORIES_VALUE)
    var calories: Int = 0,

    @ColumnInfo(name = CALORIES_DATE)
    var date: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (other !is CalorieRecord) {
            return false
        }
        if (other.id == id && other.name == name && other.calories == calories && other.date == date) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + calories
        result = 31 * result + date.hashCode()
        return result
    }
}