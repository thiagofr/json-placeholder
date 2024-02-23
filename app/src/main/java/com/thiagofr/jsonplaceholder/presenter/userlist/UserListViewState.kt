package com.thiagofr.jsonplaceholder.presenter.userlist

import com.thiagofr.jsonplaceholder.model.UserUI

sealed class UserListViewState {
    data class SetUserList(val userList: List<UserUI>) : UserListViewState()
}