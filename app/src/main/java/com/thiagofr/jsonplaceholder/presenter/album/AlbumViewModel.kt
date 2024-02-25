package com.thiagofr.jsonplaceholder.presenter.album

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.usecase.GetAlbumUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.ImageDownloaderUseCase
import com.thiagofr.jsonplaceholder.model.AlbumUI
import com.thiagofr.jsonplaceholder.model.PhotoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val imageDownloaderUseCase: ImageDownloaderUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<AlbumViewState>()
    val viewState: LiveData<AlbumViewState> = _viewState

    private var photoTemp: PhotoUI? = null

    fun dispatchAction(action: AlbumViewAction) {
        when (action) {
            is AlbumViewAction.InitViewAction -> handleInitView(action.albumUI)
            is AlbumViewAction.GetImageAction -> downloadImage(action.photo)
            is AlbumViewAction.RequestPermissionAction -> handleRequestPermission(action)
            AlbumViewAction.GetImageAfterPermission -> handleGetImageAfterPermission()
        }
    }

    private fun handleGetImageAfterPermission() {
        photoTemp?.let { downloadImage(it) }
        photoTemp = null
    }

    private fun handleRequestPermission(action: AlbumViewAction.RequestPermissionAction) {
        photoTemp = action.photo
        _viewState.postValue(AlbumViewState.RequestPermissions(action.permissions))
    }

    private fun handleInitView(albumUI: AlbumUI?) {
        if (albumUI == null) {
            handleError()
        } else {
            _viewState.value = AlbumViewState.SetupViewState(albumUI)
            getAlbum(albumUI.id)
        }
    }

    private fun getAlbum(albumId: Long) = viewModelScope.launch(Dispatchers.IO) {
        // Delay meramente para possibilitar a visualização do loading
        delay(2000L)
        when (val result = getAlbumUseCase(albumId)) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }

    private fun downloadImage(photoUI: PhotoUI) = viewModelScope.launch(Dispatchers.IO) {
        val fileName = "imagem_${System.currentTimeMillis()}.jpg"

        when (val result = imageDownloaderUseCase(photoUI.url, fileName)) {
            is Result.Success -> handleDownloadSuccess(result.data)
            else -> handleError()
        }

    }

    private fun handleDownloadSuccess(data: Uri?) {
        if (data != null) {
            _viewState.postValue(AlbumViewState.OpenImage(data))
        } else {
            handleError()
        }
    }

    private fun handleSuccess(data: List<PhotoUI>) {
        _viewState.postValue(AlbumViewState.SetAlbum(data))
    }

    private fun handleError() {
        _viewState.postValue(AlbumViewState.ErrorViewState)
    }

}