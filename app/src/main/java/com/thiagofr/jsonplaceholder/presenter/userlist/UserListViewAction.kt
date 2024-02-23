package com.thiagofr.jsonplaceholder.presenter.userlist

sealed class UserListViewAction {
    data object Init: UserListViewAction()
}