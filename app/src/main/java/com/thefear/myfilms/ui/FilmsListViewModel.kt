package com.thefear.myfilms.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.repository.Repository

class FilmsListViewModel(private val repository: Repository) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    //fun getFilms() = getFilmsFromMySource()

    //TODO
/*    fun getFilmsFromMySource(genre: Genre) = getFilmsFromGenre(genre)

    fun getComedy() = getFilmsFromGenre(Genre.COMEDY)
    fun getAction() = getFilmsFromGenre(Genre.ACTION)
    fun getCartoon() = getFilmsFromGenre(Genre.CARTOON)
    fun getThriller() = getFilmsFromGenre(Genre.THRILLER)

    private fun getFilmsFromGenre(genre: Genre) {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(AppState.Success(repository.getMyFilmsFromGenre(genre)))
        }.start()
    }*/
/*
    private fun getFilmsFromMySource() {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(AppState.Success(repository.getMyFilms()))
        }.start()
    }*/

    fun getServerFilms() {
        liveData.value = AppState.Loading
        Thread {
            liveData.postValue(AppState.Success(repository.getFilmsFromServer()))
        }.start()
    }

}
