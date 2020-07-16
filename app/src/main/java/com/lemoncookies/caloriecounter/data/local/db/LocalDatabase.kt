package com.lemoncookies.caloriecounter.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.dao.CalorieDao
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.local.utils.Constants.LOCAL_DB_NAME
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Database(entities = [CalorieRecord::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        var TEST_MODE = false

        fun getDatabase(context: Context): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    if (TEST_MODE) {
                        Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
                            .allowMainThreadQueries().build()
                    } else {
                        Room.databaseBuilder(
                            context.applicationContext,
                            LocalDatabase::class.java, LOCAL_DB_NAME
                        ).build()
                    }
                INSTANCE = instance
                return instance
            }
        }

        private fun close() {
            INSTANCE?.close()
        }
    }
}