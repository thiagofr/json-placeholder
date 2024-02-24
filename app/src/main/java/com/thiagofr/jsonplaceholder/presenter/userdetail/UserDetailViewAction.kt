package com.thiagofr.jsonplaceholder.presenter.userdetail

import com.thiagofr.jsonplaceholder.model.UserUI

sealed class UserDetailViewAction {
    data class InitView(val user: UserUI?) : UserDetailViewAction()
}