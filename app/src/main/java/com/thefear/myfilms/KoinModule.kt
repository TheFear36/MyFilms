package com.thefear.myfilms

import com.thefear.myfilms.model.repository.Repository
import com.thefear.myfilms.model.repository.RepositoryImpl
import com.thefear.myfilms.ui.FilmsDetailsViewModel
import com.thefear.myfilms.ui.FilmsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { FilmsListViewModel(get()) }
   // viewModel() { FilmsDetailsViewModel(get()) }
}