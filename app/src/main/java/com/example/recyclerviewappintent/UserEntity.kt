package com.example.recyclerviewappintent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var firstName: String = ""
    var lastName: String = ""
    var birthday: String = ""
    var phone: String = ""
}