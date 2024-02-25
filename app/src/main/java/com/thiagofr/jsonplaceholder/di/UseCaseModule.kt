package com.thiagofr.jsonplaceholder.di

import com.thiagofr.jsonplaceholder.domain.usecase.GetAlbumUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.GetAlbumUseCaseImpl
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserAlbumsUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserAlbumsUseCaseImpl
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCaseImpl
import com.thiagofr.jsonplaceholder.domain.usecase.ImageDownloaderUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.ImageDownloaderUseCaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserListUseCase> { GetUserListUseCaseImpl(userRepository = get()) }
    factory<GetUserAlbumsUseCase> { GetUserAlbumsUseCaseImpl(userRepository = get()) }
    factory<GetAlbumUseCase> { GetAlbumUseCaseImpl(albumRepository = get()) }
    factory<ImageDownloaderUseCase> { ImageDownloaderUseCaseImpl(application = androidApplication()) }
}