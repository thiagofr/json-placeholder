package com.thiagofr.jsonplaceholder.presenter.album

import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.PhotoUI

sealed class AlbumViewAction {
    class InitViewAction(val albumUI: AlbumUI?): AlbumViewAction()
    class GetImageAction(val photo: PhotoUI) : AlbumViewAction()
    class RequestPermissionAction(val photo: PhotoUI, val permissions: Array<String>) : AlbumViewAction()
    data object GetImageAfterPermission : AlbumViewAction()
}