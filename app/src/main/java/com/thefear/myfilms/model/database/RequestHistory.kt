package com.thefear.myfilms.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RequestHistory(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val poster: String,
    val title: String,
    val year: String,
    val rate: String
)
