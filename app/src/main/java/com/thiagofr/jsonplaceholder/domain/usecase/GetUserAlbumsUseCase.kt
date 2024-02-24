package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.model.AlbumUI

interface GetUserAlbumsUseCase {
    suspend operator fun invoke(userId: Long): Result<List<AlbumUI>>
}