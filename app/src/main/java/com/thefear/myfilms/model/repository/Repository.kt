package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.entities.Film

interface Repository {

    fun getMyFilms(): MutableList<Film>
    fun getMyFilmsFromGenre(genre: Genre): MutableList<Film>
    fun getFilmsFromServer(): MutableList<Film>
}