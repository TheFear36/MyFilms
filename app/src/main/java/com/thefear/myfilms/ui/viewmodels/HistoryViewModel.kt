package com.thefear.myfilms.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.BDState
import com.thefear.myfilms.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: Repository
) : ViewModel() {
    val historyLiveData: MutableLiveData<BDState> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.value = BDState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            historyLiveData.postValue(BDState.Success(repository.getAllHistory()))
        }
    }
}