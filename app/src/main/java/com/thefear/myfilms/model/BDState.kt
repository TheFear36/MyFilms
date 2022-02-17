package com.thefear.myfilms.model

import com.thefear.myfilms.model.entities.Film

sealed interface BDState {
    data class Success(val filmsData: MutableList<Film>) : BDState
    data class Error(val error: Throwable) : BDState
    object Loading : BDState
}