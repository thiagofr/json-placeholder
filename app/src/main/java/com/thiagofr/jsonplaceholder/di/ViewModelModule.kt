package com.thiagofr.jsonplaceholder.di

import com.thiagofr.jsonplaceholder.presenter.userdetail.UserDetailViewModel
import com.thiagofr.jsonplaceholder.presenter.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserListViewModel(
        getUserListUseCase = get()
    ) }
    viewModel {
        UserDetailViewModel(
            getUserAlbumsUseCase = get()
        )
    }
}