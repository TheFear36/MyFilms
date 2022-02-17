package com.thefear.myfilms

import com.thefear.myfilms.model.repository.Repository
import com.thefear.myfilms.model.repository.RepositoryImpl
import com.thefear.myfilms.ui.viewmodels.DetailsViewModel
import com.thefear.myfilms.ui.viewmodels.FilmsListViewModel
import com.thefear.myfilms.ui.viewmodels.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { FilmsListViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}