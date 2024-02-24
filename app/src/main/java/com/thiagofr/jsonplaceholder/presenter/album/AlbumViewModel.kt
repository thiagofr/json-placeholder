package com.thiagofr.jsonplaceholder.presenter.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.usecase.GetAlbumUseCase
import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.PhotoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
): ViewModel() {

    private val _viewState = MutableLiveData<AlbumViewState>()
    val viewState: LiveData<AlbumViewState> = _viewState

    fun dispatchAction(action: AlbumViewAction) {
        when (action) {
            is AlbumViewAction.InitView -> handleInitView(action.albumUI)
        }
    }

    private fun handleInitView(albumUI: AlbumUI?) {
        if (albumUI == null) {
            handleError()
        } else {
            _viewState.value = AlbumViewState.SetupViewState(albumUI)
            getAlbum(albumUI.id)
        }
    }

    private fun getAlbum(albumId: Long) = viewModelScope.launch(Dispatchers.IO){
        // Delay meramente para possibilitar a visualização do loading
        delay(2000L)
        when (val result = getAlbumUseCase(albumId)) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }

    private fun handleSuccess(data: List<PhotoUI>) {
        _viewState.postValue(AlbumViewState.SetAlbum(data))
    }

    private fun handleError() {
        _viewState.postValue(AlbumViewState.ErrorViewState)
    }

}