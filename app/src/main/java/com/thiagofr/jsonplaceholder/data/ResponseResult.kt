package com.thiagofr.jsonplaceholder.data

import java.lang.Exception

sealed class ResponseResult<T>{
    data class SuccessResponse<T>(
        val data: T
    ) : ResponseResult<T>()

    data class ErrorResponse<T>(
        val exception: Exception
    ) : ResponseResult<T>()
}