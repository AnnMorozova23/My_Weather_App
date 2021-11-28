package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.database.Database
import com.example.myweatherapp.modul.database.HistoryEntity
import com.example.myweatherapp.modul.entities.City
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

    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(Weather()))
    }


    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(Database.db.historyDao().all())
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }
    }


    private fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(
            0, weather.city.city,
            weather.temperature,
            weather.condition ?: ""
        )
    }


}