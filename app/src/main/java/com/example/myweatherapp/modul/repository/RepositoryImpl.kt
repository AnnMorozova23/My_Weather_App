package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather
import com.example.myweatherapp.modul.entities.getRussianCities
import com.example.myweatherapp.modul.entities.getWorldCities
import com.example.myweatherapp.modul.rest.WeatherRepo
import com.example.myweatherapp.modul.rest_entities.WeatherLoader

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat,lon).execute().body()
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.conditions
        )
    }

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()


}