package com.thiagofr.jsonplaceholder.data.repository

import com.thiagofr.jsonplaceholder.data.Photo
import com.thiagofr.jsonplaceholder.data.ResponseResult

interface AlbumRepository {
    suspend fun getAlbum(albumId: Long): ResponseResult<List<Photo>>
}