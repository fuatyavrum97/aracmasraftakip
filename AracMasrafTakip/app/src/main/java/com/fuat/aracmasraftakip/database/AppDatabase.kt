package com.fuat.aracmasraftakip.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fuat.aracmasraftakip.database.entity.CategoryEntity
import com.fuat.aracmasraftakip.database.entity.ExpenseEntity
import com.fuat.aracmasraftakip.database.entity.SettingsEntity
import com.fuat.aracmasraftakip.database.entity.VehicleEntity


@Database(entities = [CategoryEntity::class, ExpenseEntity::class, SettingsEntity::class, VehicleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aractakipdb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}