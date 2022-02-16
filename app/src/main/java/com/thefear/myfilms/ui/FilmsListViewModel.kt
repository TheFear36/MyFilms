package com.thefear.myfilms.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.repository.Repository

class FilmsListViewModel(private val repository: Repository) : ViewModel() {

    private val liveDataMove = MutableLiveData<AppState>()
    private val liveDataTV = MutableLiveData<AppState>()
    private val liveDataCartoon = MutableLiveData<AppState>()
    private val liveDataAnime = MutableLiveData<AppState>()

    fun getLiveDataMove(): LiveData<AppState> = liveDataMove
    fun getLiveDataTV(): LiveData<AppState> = liveDataTV
    fun getLiveDataCartoon(): LiveData<AppState> = liveDataCartoon
    fun getLiveDataAnime(): LiveData<AppState> = liveDataAnime

    fun getServerFilms() {
        liveDataMove.value = AppState.Loading
        Thread {
            liveDataMove.postValue(AppState.SuccessMove(repository.getMoveFromServer()))
            liveDataMove.postValue(AppState.SuccessTV(repository.getTvSeriesFromServer()))
            liveDataMove.postValue(AppState.SuccessCartoon(repository.getCartoonFromServer()))
            liveDataMove.postValue(AppState.SuccessAnime(repository.getAnimeFromServer()))
        }.start()
    }
}
