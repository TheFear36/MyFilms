package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.FilmsLoader
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.entities.FilmsFeed

class RepositoryImpl : Repository {

    private val filmsFeed: FilmsFeed = FilmsFeed()
    private val films = filmsFeed.getFilms()

    override fun getMyFilms(): MutableList<Film> {
        return filmsFeed.getFilms()
    }

    override fun getMyFilmsFromGenre(genre: Genre): MutableList<Film> {
        val tvSeries = mutableListOf<Film>()
        val move = mutableListOf<Film>()
        val cartoon = mutableListOf<Film>()
        val anime = mutableListOf<Film>()
        for (i in films) {
            when (i.style) {
                Genre.TV_SERIES -> tvSeries.add(i)
                Genre.MOVE -> move.add(i)
                Genre.CARTOON -> cartoon.add(i)
                Genre.ANIME -> anime.add(i)
            }
        }
        return when (genre) {
            Genre.TV_SERIES -> tvSeries
            Genre.MOVE -> move
            Genre.CARTOON -> cartoon
            Genre.ANIME -> anime
        }
    }


    override fun getFilmsFromServer(): MutableList<Film> {
        val dto = FilmsLoader.loadFilms()
        val data: MutableList<Film> = mutableListOf()

        dto?.docs?.forEach {
            data.add(
                Film(
                    cover = it.poster?.url.toString(),
                    title = it.name.toString(),
                    year = it.year.toString(),
                    rate = it.rating?.kp.toString(),
                    style = Genre.MOVE,
                    details = it.description.toString()
                )
            )
        }



        return data
    }
}