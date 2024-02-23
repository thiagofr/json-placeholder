package com.thiagofr.jsonplaceholder.data.repository

import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.User

interface UserRepository {
    suspend fun getUserList(): ResponseResult<List<User>>
}