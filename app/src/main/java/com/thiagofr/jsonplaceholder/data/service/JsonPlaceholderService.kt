package com.thiagofr.jsonplaceholder.data.service

import com.thiagofr.jsonplaceholder.data.Album
import com.thiagofr.jsonplaceholder.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderService {
    @GET("users")
    suspend fun getUserList(): Response<List<User>>
    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(
        @Path("userId") userId: Long
    ): Response<List<Album>>
}