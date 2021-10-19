package com.example.myweatherapp.di

import com.example.myweatherapp.ui.main.MainViewModel
import com.example.myweatherapp.modul.repository.Repository
import com.example.myweatherapp.modul.repository.RepositoryImpl
import com.example.myweatherapp.ui.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }

    viewModel { DetailsViewModel(get()) }
}