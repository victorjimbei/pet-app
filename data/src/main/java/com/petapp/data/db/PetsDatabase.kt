package com.petapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.petapp.data.db.PetsDatabase.Companion.ROOM_DATABASE_VERSION
import com.petapp.data.local.dao.PetsDao
import com.petapp.data.local.model.PetEntity


@Database(
    version = ROOM_DATABASE_VERSION,
    entities = [
        PetEntity::class
    ],
    exportSchema = true
)
@TypeConverters(PetConverters::class)
abstract class PetsDatabase : RoomDatabase() {

    companion object {
        private const val ROOM_DATABASE_NAME = "room-pets.db"
        const val ROOM_DATABASE_VERSION = 1

        // For Singleton instantiation
        @Volatile
        private var instance: PetsDatabase? = null

        fun getInstance(context: Context): PetsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PetsDatabase {
            return Room
                .databaseBuilder(context.applicationContext, PetsDatabase::class.java, ROOM_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun petsDao(): PetsDao
}