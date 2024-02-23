package com.thiagofr.jsonplaceholder.data.service

import com.thiagofr.jsonplaceholder.data.User
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholderService {
    @GET("users")
    suspend fun getUserList(): Response<List<User>>
}