package com.thefear.myfilms.model.entities

import android.os.Parcelable
import com.thefear.myfilms.model.repository.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val cover: String,
    val title: String,
    val year: String,
    val rate: String,
    val style: Genre,
    val details: String
) : Parcelable

