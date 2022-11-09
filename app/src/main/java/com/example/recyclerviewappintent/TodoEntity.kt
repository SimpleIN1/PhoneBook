package com.example.recyclerviewappintent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var content: String = ""
}