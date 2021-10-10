package com.example.myweatherapp.di

import com.example.myweatherapp.framework.main.MainViewModel
import com.example.myweatherapp.modul.repository.Repository
import com.example.myweatherapp.modul.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}