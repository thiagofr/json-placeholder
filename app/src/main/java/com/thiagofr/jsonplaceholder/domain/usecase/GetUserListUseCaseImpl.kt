package com.thiagofr.jsonplaceholder.domain.usecase

import com.thiagofr.jsonplaceholder.data.ResponseResult
import com.thiagofr.jsonplaceholder.data.User
import com.thiagofr.jsonplaceholder.data.repository.UserRepository
import com.thiagofr.jsonplaceholder.model.UserUI
import com.thiagofr.jsonplaceholder.domain.Result
import com.thiagofr.jsonplaceholder.domain.mapper.UserToUserUIMapper

class GetUserListUseCaseImpl(private val userRepository: UserRepository) : GetUserListUseCase {
    override suspend fun invoke(): Result<List<UserUI>> {
        return when (val result = userRepository.getUserList()) {
            is ResponseResult.SuccessResponse -> handleSuccess(result)
            is ResponseResult.ErrorResponse -> handleError(result)
        }
    }

    private fun handleSuccess(result: ResponseResult.SuccessResponse<List<User>>): Result<List<UserUI>> {
        val userList = result.data.map(UserToUserUIMapper::map)
        return Result.Success(
            userList
        )
    }

    private fun handleError(result: ResponseResult.ErrorResponse<List<User>>): Result<List<UserUI>> {
        return Result.Error(
            result.exception
        )
    }
}