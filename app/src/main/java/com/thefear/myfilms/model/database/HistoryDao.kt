package com.thefear.myfilms.model.database

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM RequestHistory")
    fun all(): List<RequestHistory>

    @Query("SELECT * FROM RequestHistory WHERE title LIKE :title")
    fun getDataByTitle(title: String): List<RequestHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RequestHistory)

    @Update
    fun update(entity: RequestHistory)

    @Delete
    fun delete(entity: RequestHistory)
}