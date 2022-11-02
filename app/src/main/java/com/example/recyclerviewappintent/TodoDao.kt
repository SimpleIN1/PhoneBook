package com.example.recyclerviewappintent

import androidx.room.Dao
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @get:Query("select * from todos")
    val all: List<TodoEntity>

    @Query("select * from todos where id = :id")
    fun getById(id: Long): TodoEntity

    @Insert
    fun insert(todo: TodoEntity): Long

    @Update
    fun update(todo: TodoEntity)

    @Delete
    fun delete(todo: TodoEntity)
}