package com.thiagofr.jsonplaceholder.presenter.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCase
import com.thiagofr.jsonplaceholder.model.UserUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<UserListViewState>()
    val viewState: LiveData<UserListViewState> = _viewState

    fun dispatchAction(action: UserListViewAction) {
        when (action) {
            UserListViewAction.Init -> handleInit()
        }
    }

    private fun handleInit() = viewModelScope.launch(Dispatchers.IO) {
        _viewState.postValue(UserListViewState.Loading)
        // Delay meramente para possibilitar a visualização do loading
        delay(2000L)
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