package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.entities.FilmsFeed

class RepositoryImpl : Repository {

    private val filmsFeed: FilmsFeed = FilmsFeed()
    private val films = filmsFeed.getFilms()

    override fun getMyFilms(): MutableList<Film> {
        return filmsFeed.getFilms()
    }

    override fun getMyFilmsFromGenre(genre: Genre): MutableList<Film> {
        val comedy = mutableListOf<Film>()
        val action = mutableListOf<Film>()
        val cartoon = mutableListOf<Film>()
        val thriller = mutableListOf<Film>()
        for (i in films) {
            when (i.style) {
                Genre.COMEDY -> comedy.add(i)
                Genre.ACTION -> action.add(i)
                Genre.CARTOON -> cartoon.add(i)
                Genre.THRILLER -> thriller.add(i)
            }
        }
        return when (genre) {
            Genre.COMEDY -> comedy
            Genre.ACTION -> action
            Genre.CARTOON -> cartoon
            Genre.THRILLER -> thriller
        }
    }


    override fun getFilmsFromServer(): MutableList<Film> {
        TODO("Not yet implemented")
    }
}