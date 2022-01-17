package com.thefear.myfilms.model

import com.thefear.myfilms.model.entities.Film

sealed interface AppState {
    data class Success(val filmsData: MutableList<Film>) : AppState
    data class SuccessOne(val filmData: Film) : AppState
    data class Error(val error: Throwable) : AppState
    object Loading : AppState
}