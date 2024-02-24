package com.thiagofr.jsonplaceholder.presenter.album

import com.thiagofr.jsonplaceholder.model.AlbumUI

sealed class AlbumViewState {
    data class SetupViewState(val albumUI: AlbumUI): AlbumViewState()
    data object ErrorViewState: AlbumViewState()
}