package com.picpay.desafio.userlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import com.picpay.desafio.common.base.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListViewModel(
    private val useCase: GetUserListUseCase
) : ViewModel() {

    private val _userListResponse = MutableLiveData<ViewState<List<User>>>()
    val userListResponse: LiveData<ViewState<List<User>>>
        get() = _userListResponse

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch {
            useCase.execute(Unit)
                .collect {
                    handleResponse(it)
                }
        }
    }

    private fun handleResponse(response: Resource<List<User>>) {
        val result = when (response) {
            Resource.Empty -> ViewState(isEmpty = true)
            is Resource.Error -> ViewState(error = response.error)
            Resource.Loading -> ViewState(isLoading = true)
            is Resource.Success -> ViewState(result = response.data)
        }

        _userListResponse.postValue(result)
    }

}