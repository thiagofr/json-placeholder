package com.thiagofr.jsonplaceholder.di

import com.thiagofr.jsonplaceholder.domain.usecase.GetUserAlbumsUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserAlbumsUseCaseImpl
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCase
import com.thiagofr.jsonplaceholder.domain.usecase.GetUserListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserListUseCase> { GetUserListUseCaseImpl(userRepository = get()) }
    factory<GetUserAlbumsUseCase> { GetUserAlbumsUseCaseImpl(userRepository = get()) }
}