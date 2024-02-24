package com.thiagofr.jsonplaceholder.presenter.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserAlbumsUseCase
import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.UserUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(
    val getUserAlbumsUseCase: GetUserAlbumsUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<UserDetailViewState>()
    val viewState: LiveData<UserDetailViewState> = _viewState

    fun dispatchAction(action: UserDetailViewAction) {
        when (action) {
            is UserDetailViewAction.InitView -> handleInitView(action.user)
        }
    }

    private fun handleInitView(user: UserUI?) {
        if (user == null) {
            handleError()
        } else {
            _viewState.value = UserDetailViewState.SetupViewState(user)
            getAlbums(user.id)
        }
    }

    private fun getAlbums(userId: Long) = viewModelScope.launch(Dispatchers.IO){

        when (val result = getUserAlbumsUseCase(userId)) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }

    private fun handleSuccess(data: List<AlbumUI>) {
        _viewState.postValue(UserDetailViewState.SetAlbumsViewState(data))
    }

    private fun handleError() {
        _viewState.postValue(UserDetailViewState.ErrorViewState)
    }
}