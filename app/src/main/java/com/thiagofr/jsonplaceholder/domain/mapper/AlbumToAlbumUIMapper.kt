package com.thiagofr.jsonplaceholder.domain.mapper

import com.thiagofr.jsonplaceholder.data.Album
import com.thiagofr.jsonplaceholder.model.AlbumUI

object AlbumToAlbumUIMapper {
    fun map(from: Album) = AlbumUI(
        id = from.id,
        userId = from.userId,
        title = from.title,
    )
}