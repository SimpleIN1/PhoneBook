package com.example.recyclerviewappintent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = true
)
abstract class PhoneBookDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: PhoneBookDatabase? = null

        fun getDatabase(context: Context): PhoneBookDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): PhoneBookDatabase{
            return  Room.databaseBuilder(
                context.applicationContext,
                PhoneBookDatabase::class.java,
                "phonebookdb"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}