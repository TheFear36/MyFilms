package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.entities.Film

interface Repository {

    fun getMoveFromServer(): MutableList<Film>
    fun getTvSeriesFromServer(): MutableList<Film>
    fun getCartoonFromServer(): MutableList<Film>
    fun getAnimeFromServer(): MutableList<Film>
}