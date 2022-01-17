package com.thefear.myfilms.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.repository.Repository
import java.lang.Thread.sleep

class FilmsListViewModel(private val repository: Repository) : ViewModel(){

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData() : LiveData<AppState> = liveData

    fun getFilmsFromMySource() = getDataFromMySource()

    private fun getDataFromMySource() {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(AppState.Success(repository.getMyFilms()))
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
    }

}
