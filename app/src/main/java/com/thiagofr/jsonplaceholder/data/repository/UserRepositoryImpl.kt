package com.thiagofr.jsonplaceholder.data.repository

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
                true -> getErrorResponse(response)
                false -> ResponseResult.SuccessResponse(
                    userList
                )
            }

        } else {
            getErrorResponse(response)
        }
    }

    private fun getErrorResponse(response: Response<*>): ResponseResult<List<User>> {
        return ResponseResult.ErrorResponse(
            HttpException(response)
        )
    }

}