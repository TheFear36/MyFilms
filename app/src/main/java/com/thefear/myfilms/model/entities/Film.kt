package com.thefear.myfilms.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val cover: String,
    val title: String,
    val year: String,
    val rate: String,
    val details: String
) : Parcelable

