package com.thefear.myfilms.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.entities.Film

class FilmsDetailsViewModel(): ViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData() : LiveData<AppState> = liveData

    fun update(film: Film) {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(100)
            liveData.postValue(AppState.SuccessOne(film))
        }.start()
    }



    override fun onCleared() {
        super.onCleared()
    }

}