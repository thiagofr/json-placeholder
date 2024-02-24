package com.thiagofr.jsonplaceholder.presenter.album

import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.PhotoUI

sealed class AlbumViewState {
    data class SetupViewState(val albumUI: AlbumUI): AlbumViewState()
    data object ErrorViewState: AlbumViewState()
    data class SetAlbum(val album: List<PhotoUI>): AlbumViewState()
}