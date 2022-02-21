@file:Suppress("SpellCheckingInspection")

package com.picpay.desafio.userlist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.picpay.desafio.userlist.data.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserListDao {
    @Insert(onConflict = REPLACE)
    fun saveList(list: List<UserEntity>)
    @Query("SELECT * FROM userentity")
    fun getList(): Flow<List<UserEntity>>
}