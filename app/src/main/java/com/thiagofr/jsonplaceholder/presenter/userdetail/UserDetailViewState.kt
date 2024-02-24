package com.thiagofr.jsonplaceholder.presenter.userdetail

import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.UserUI

sealed class UserDetailViewState {
    data class SetupViewState(
        val userUI: UserUI
    ) : UserDetailViewState()

    data class SetAlbumsViewState(
        val albumList: List<AlbumUI>
    ) : UserDetailViewState()

    data object ErrorViewState: UserDetailViewState()
}