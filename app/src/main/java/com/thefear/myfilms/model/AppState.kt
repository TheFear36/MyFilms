package com.thefear.myfilms.model

import com.thefear.myfilms.model.entities.Film

sealed interface AppState {
    data class SuccessMove(val filmsData: MutableList<Film>) : AppState
    data class SuccessTV(val filmsData: MutableList<Film>) : AppState
    data class SuccessCartoon(val filmsData: MutableList<Film>) : AppState
    data class SuccessAnime(val filmsData: MutableList<Film>) : AppState
    data class Error(val error: Throwable) : AppState
    object Loading : AppState
}