package com.thiagofr.jsonplaceholder.presenter.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCase
import com.thiagofr.jsonplaceholder.model.UserUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<UserListViewState>()
    val viewState: LiveData<UserListViewState> = _viewState

    fun dispatchAction(action: UserListViewAction) {
        when (action) {
            UserListViewAction.Init -> handleInit(action)
        }
    }

    private fun handleInit(action: UserListViewAction) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getUserListUseCase()) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }

    private fun handleSuccess(data: List<UserUI>) {
        _viewState.postValue(UserListViewState.SetUserList(userList = data))
    }

    private fun handleError() {

    }


}