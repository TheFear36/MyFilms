package com.thefear.myfilms.model.repository

import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.repository.forretrofit.MoveRepo

class RepositoryImpl : Repository {

    override fun getMoveFromServer(): MutableList<Film> = getMoveToType(1)

    override fun getTvSeriesFromServer(): MutableList<Film> = getMoveToType(2)

    override fun getCartoonFromServer(): MutableList<Film> = getMoveToType(3)

    override fun getAnimeFromServer(): MutableList<Film> = getMoveToType(4)

    private fun getMoveToType(type: Int) : MutableList<Film> {

        val dto = MoveRepo.adapter.getMove("rating.kp", "6-10",
            "typeNumber", type,
            "year", "2020-2022",
            "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06").execute().body()
        val data: MutableList<Film> = mutableListOf()

        dto?.docs?.forEach {
            data.add(
                Film(
                    cover = it.poster?.url.toString(),
                    title = it.name.toString(),
                    year = it.year.toString(),
                    rate = it.rating?.kp.toString(),
                    details = it.description.toString()
                )
            )
        }

        return data
    }
}