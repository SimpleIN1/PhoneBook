package com.example.recyclerviewappintent

import androidx.room.Dao
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @get:Query("select * from user")
    val all: List<UserEntity>

    @Query("select * from user where id = :id")
    fun getById(id: Long): UserEntity

    @Insert
    fun insert(user: UserEntity): Long

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}