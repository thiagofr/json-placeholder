package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.data.Album
import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.repository.UserRepository
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.mapper.AlbumToAlbumUIMapper
import com.thiagofr.jsonplaceholder.model.AlbumUI

class GetUserAlbumsUseCaseImpl(
    private val userRepository: UserRepository
): GetUserAlbumsUseCase {
    override suspend fun invoke(userId: Long): Result<List<AlbumUI>> {
        return when (val result = userRepository.getUserAlbums(userId)) {
            is ResponseResult.SuccessResponse -> handleSuccess(result)
            is ResponseResult.ErrorResponse -> handleError(result)
        }
    }

    private fun handleSuccess(result: ResponseResult.SuccessResponse<List<Album>>): Result<List<AlbumUI>> {
        val albumList = result.data.map(AlbumToAlbumUIMapper::map)
        return Result.Success(
            albumList
        )
    }

    private fun handleError(result: ResponseResult.ErrorResponse<List<Album>>): Result<List<AlbumUI>> {
        return Result.Error(
            result.exception
        )
    }
}