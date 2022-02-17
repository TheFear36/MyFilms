package com.thefear.myfilms.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    fun saveEntity(film: Film) = with(viewModelScope) {
        launch(Dispatchers.IO) {
            repository.saveEntity(film)
        }
    }

}