package com.thefear.myfilms.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.thefear.myfilms.App

@androidx.room.Database(
    entities = [
        RequestHistory::class
    ],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DB_NAME = "request_history.db"

        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}