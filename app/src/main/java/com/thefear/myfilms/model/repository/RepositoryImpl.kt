package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.entities.FilmsFeed

class RepositoryImpl : Repository {

    private val filmsFeed: FilmsFeed = FilmsFeed()

    override fun getMyFilms(): MutableList<Film> {
        return filmsFeed.getFilms()
    }

    override fun getFilmsFromServer(): MutableList<Film> {
        TODO("Not yet implemented")
    }
}