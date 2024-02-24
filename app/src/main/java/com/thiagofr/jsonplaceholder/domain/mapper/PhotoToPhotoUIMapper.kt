package com.thiagofr.jsonplaceholder.domain.mapper

import com.thiagofr.jsonplaceholder.data.Photo
import com.thiagofr.jsonplaceholder.model.PhotoUI

object PhotoToPhotoUIMapper {
    fun map(from: Photo) = PhotoUI(
        id = from.id,
        albumId = from.albumId,
        title = from.title,
        thumbnailUrl = from.thumbnailUrl,
        url = from.url,
    )
}