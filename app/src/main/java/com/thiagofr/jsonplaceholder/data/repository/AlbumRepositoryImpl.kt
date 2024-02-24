package com.thiagofr.jsonplaceholder.data.repository

import com.thiagofr.jsonplaceholder.data.Photo
import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.service.JsonPlaceholderService
import retrofit2.HttpException
import retrofit2.Response

class AlbumRepositoryImpl(
    private val service: JsonPlaceholderService
) : AlbumRepository {
    override suspend fun getAlbum(albumId: Long): ResponseResult<List<Photo>> {
        val response = service.getAlbum(albumId)

        return if (response.isSuccessful) {

            val album = response.body()

            when (album.isNullOrEmpty()) {
                true -> getErrorResponse(response)
                false -> ResponseResult.SuccessResponse(
                    album
                )
            }

        } else {
            getErrorResponse(response)
        }
    }

    private fun getErrorResponse(response: Response<*>): ResponseResult<List<Photo>> {
        return ResponseResult.ErrorResponse(
            HttpException(response)
        )
    }
}