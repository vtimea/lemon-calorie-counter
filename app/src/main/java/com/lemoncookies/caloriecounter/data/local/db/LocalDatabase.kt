package com.lemoncookies.caloriecounter.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lemoncookies.caloriecounter.data.local.dao.CalorieDao
import com.lemoncookies.caloriecounter.data.local.dao.WeightDao
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.local.entities.WeightRecord

@Database(entities = [CalorieRecord::class, WeightRecord::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao
    abstract fun weightDao(): WeightDao

    companion object {

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LocalDatabase::class.java, "local_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}