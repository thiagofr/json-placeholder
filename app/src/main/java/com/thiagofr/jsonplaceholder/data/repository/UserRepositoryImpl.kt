package com.thiagofr.jsonplaceholder.data.repository

import com.thiagofr.jsonplaceholder.data.Album
import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.User
import com.thiagofr.jsonplaceholder.data.service.JsonPlaceholderService
import retrofit2.HttpException
import retrofit2.Response

class UserRepositoryImpl(
    private val service: JsonPlaceholderService
) : UserRepository {
    override suspend fun getUserList(): ResponseResult<List<User>> {
        val response = service.getUserList()

        return if (response.isSuccessful) {

            val userList = response.body()

            when (userList.isNullOrEmpty()) {
                true -> getErrorUserResponse(response)
                false -> ResponseResult.SuccessResponse(
                    userList
                )
            }

        } else {
            getErrorUserResponse(response)
        }
    }

    override suspend fun getUserAlbums(userId: Long): ResponseResult<List<Album>> {
        val response = service.getUserAlbums(userId)

        return if (response.isSuccessful) {

            val albumList = response.body()

            when (albumList.isNullOrEmpty()) {
                true -> getErrorUserAlbumsResponse(response)
                false -> ResponseResult.SuccessResponse(
                    albumList
                )
            }

        } else {
            getErrorUserAlbumsResponse(response)
        }
    }

    private fun getErrorUserResponse(response: Response<*>): ResponseResult<List<User>> {
        return ResponseResult.ErrorResponse(
            HttpException(response)
        )
    }
    private fun getErrorUserAlbumsResponse(response: Response<*>): ResponseResult<List<Album>> {
        return ResponseResult.ErrorResponse(
            HttpException(response)
        )
    }

}