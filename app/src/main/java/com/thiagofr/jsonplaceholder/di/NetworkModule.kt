package com.thiagofr.jsonplaceholder.di

import com.thiagofr.jsonplaceholder.data.repository.AlbumRepository
import com.thiagofr.jsonplaceholder.data.repository.AlbumRepositoryImpl
import com.thiagofr.jsonplaceholder.data.repository.UserRepository
import com.thiagofr.jsonplaceholder.data.repository.UserRepositoryImpl
import com.thiagofr.jsonplaceholder.data.service.JsonPlaceholderService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val service = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(JsonPlaceholderService::class.java)

val networkModule = module {
    single<JsonPlaceholderService> { service }
    factory<UserRepository> { UserRepositoryImpl(service = get()) }
    factory<AlbumRepository> { AlbumRepositoryImpl(service = get()) }
}