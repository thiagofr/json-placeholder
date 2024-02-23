package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.model.UserUI
import com.thiagofr.jsonplaceholder.domain.Result

interface GetUserListUseCase {
    operator fun invoke(): Result<List<UserUI>>
}