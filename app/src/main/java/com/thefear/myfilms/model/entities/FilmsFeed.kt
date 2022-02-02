package com.thefear.myfilms.model.entities

import com.github.javafaker.Faker
import com.thefear.myfilms.model.repository.Genre

class FilmsFeed {

    private var films = mutableListOf<Film>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        STYLES.shuffle()
        films = (1..10).map {
            Film(
                title = faker.name().name(),
                year = faker.number().numberBetween(1890L, 2022L).toString(),
                rate = faker.number().randomDouble(1, 1, 5).toString(),
                cover = IMAGES[it % IMAGES.size],
                style = STYLES[it % STYLES.size],
                details = faker.lorem().paragraph(5)
            )
        }.toMutableList()
    }

    fun getFilms(): MutableList<Film> {
        return films
    }


    companion object {
        private val IMAGES = mutableListOf(
            "http://wallpapers-images.ru/2560x1440/movies/wallpapers/wallpapers-movies-6.jpg",
            "https://image.tmdb.org/t/p/original/wDqK38HM4eLWEM5lCPmMDLsc3yB.jpg",
            "https://i.playground.ru/p/6xPD5RAYUKa__1prq359ZQ.jpeg",
            "https://peopletalk.ru/wp-content/uploads/2016/09/Hp-harry-potter-34907716-1280-800.jpg",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1629390/e9ab2507-b8cd-4e28-a63c-32bba8f45acc/800x800",
            "https://m.media-amazon.com/images/I/91yDM1mXXNL.jpg",
            "https://life-secrets.ru/wp-content/uploads/2018/03/mstiteli-1.jpg",
            "https://e-w-e.ru/wp-content/uploads/2016/07/1613837_1920x1080x500.jpg",
            "https://c.wallhere.com/photos/95/fd/Inception_Joseph_Gordon_Levitt_Leonardo_DiCaprio_Tom_Hardy_panels-195987.jpg!d",
            "https://s1.1zoom.ru/b4739/328/Maleficent_(film)_Angelina_Jolie_518024_1152x864.jpg",
        )

        private val STYLES = mutableListOf(
            Genre.MOVE,
            Genre.CARTOON,
            Genre.TV_SERIES,
            Genre.ANIME,
        )
    }

}