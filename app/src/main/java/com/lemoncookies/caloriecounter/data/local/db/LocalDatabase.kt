package com.lemoncookies.caloriecounter.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.dao.CalorieDao
import com.lemoncookies.caloriecounter.data.local.dao.WeightDao
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.local.entities.WeightRecord
import com.lemoncookies.caloriecounter.data.local.utils.Constants.LOCAL_DB_NAME
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Database(entities = [CalorieRecord::class, WeightRecord::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao
    abstract fun weightDao(): WeightDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        var TEST_MODE = false

        fun getDatabase(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        if (TEST_MODE) {
                            INSTANCE =
                                Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
                                    .allowMainThreadQueries().build()
                        } else {
                            INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                LocalDatabase::class.java, LOCAL_DB_NAME
                            ).build()
                        }
                    }
                }
            }
            return INSTANCE
        }

        private fun close() {
            INSTANCE?.close()
        }
    }
}