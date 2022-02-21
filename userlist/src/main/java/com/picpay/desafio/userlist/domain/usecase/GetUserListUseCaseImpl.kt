package com.picpay.desafio.userlist.domain.usecase

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow

class GetUserListUseCaseImpl(
    private val repository: UserListRepository
) : GetUserListUseCase() {
    override suspend fun execute(params: Unit): Flow<Resource<List<User>>> = repository.getList()
}