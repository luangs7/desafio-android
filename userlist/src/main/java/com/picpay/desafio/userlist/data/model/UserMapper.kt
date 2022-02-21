package com.picpay.desafio.userlist.data.model

import com.picpay.desafio.userlist.domain.model.User

fun UserResponse.toDomain(): User {
    return User(this.id, this.img, this.name, this.username)
}

fun UserResponse.toEntity(): UserEntity {
    return UserEntity(this.id, this.img, this.name, this.username)
}

fun UserEntity.toDomain(): User {
    return User(this.id, this.img, this.name, this.username)
}