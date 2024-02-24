package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.model.PhotoUI

interface GetAlbumUseCase {
    suspend operator fun invoke(albumId: Long): Result<List<PhotoUI>>
}