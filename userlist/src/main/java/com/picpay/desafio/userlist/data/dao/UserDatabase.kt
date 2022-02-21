package com.picpay.desafio.userlist.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.userlist.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserListDao

    companion object{
        const val USER_TABLE_NAME = "users"
    }
}