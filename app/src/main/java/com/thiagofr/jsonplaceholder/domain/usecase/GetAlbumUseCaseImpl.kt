package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.data.Photo
import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.repository.AlbumRepository
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.mapper.PhotoToPhotoUIMapper
import com.thiagofr.jsonplaceholder.model.PhotoUI

class GetAlbumUseCaseImpl(
    private val albumRepository: AlbumRepository
) : GetAlbumUseCase {
    override suspend fun invoke(albumId: Long): Result<List<PhotoUI>> {

        return when (val result = albumRepository.getAlbum(albumId)) {
            is ResponseResult.SuccessResponse -> handleSuccess(result)
            is ResponseResult.ErrorResponse -> handleError(result)
        }
    }

    private fun handleSuccess(result: ResponseResult.SuccessResponse<List<Photo>>): Result<List<PhotoUI>> {
        val album = result.data.map(PhotoToPhotoUIMapper::map)
        return Result.Success(
            album
        )
    }

    private fun handleError(result: ResponseResult.ErrorResponse<List<Photo>>): Result<List<PhotoUI>> {
        return Result.Error(
            result.exception
        )
    }
}