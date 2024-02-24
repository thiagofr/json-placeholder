package com.thiagofr.jsonplaceholder.presenter.album

import com.thiagofr.jsonplaceholder.model.AlbumUI

sealed class AlbumViewAction {
    class InitView(val albumUI: AlbumUI?): AlbumViewAction()
}