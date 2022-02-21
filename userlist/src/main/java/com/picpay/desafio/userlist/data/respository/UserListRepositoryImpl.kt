package com.picpay.desafio.userlist.data.respository


import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.data.dao.UserListDao
import com.picpay.desafio.userlist.data.model.UserEntity
import com.picpay.desafio.userlist.data.model.toDomain
import com.picpay.desafio.userlist.data.model.toEntity
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.security.InvalidParameterException

@DelicateCoroutinesApi
class UserListRepositoryImpl(
    private val service: PicPayService,
    private val dao: UserListDao
) : UserListRepository {

    override suspend fun getList(): Flow<Resource<List<User>>> = flow {
        getListFromServer()
            .onStart { this@flow.emit(Resource.Loading) }
            .catch {
                getListFromDatabase()
                    .catch { this@flow.emit(Resource.Error<List<User>>(it)) }
                    .collect { this@flow.emit(it) }
            }
            .collect {
                this@flow.emit(it)
            }
    }

    private suspend fun getListFromServer(): Flow<Resource<List<User>>> = flow {
        val response = service.getUsers()
        if (response.isSuccessful) {
            response.body()?.let { newList ->
                saveList(newList.map { it.toEntity() })
                emit(Resource.Success(newList.map { it.toDomain() }))
            } ?: throw InvalidParameterException()
        } else {
            throw InvalidParameterException()
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getListFromDatabase(): Flow<Resource<List<User>>> = flow {
        dao.getList().collect {
            if (it.isNotEmpty()) {
                emit(Resource.Success(it.map { list -> list.toDomain() }))
            } else {
                emit(Resource.Empty)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun saveList(newList: List<UserEntity>) {
        dao.saveList(newList)
    }
}