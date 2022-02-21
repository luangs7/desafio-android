package com.picpay.desafio.common.extensions

import com.picpay.desafio.common.base.Resource


fun <T> Resource<T>.isSuccess():Boolean = this is Resource.Success
fun <T> Resource<T>.isError():Boolean = this is Resource.Error
