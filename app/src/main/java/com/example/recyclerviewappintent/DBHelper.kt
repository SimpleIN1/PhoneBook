package com.example.recyclerviewappintent

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("""
                create table $TABLE_NAME (
                    $KEY_ID integer primary key autoincrement,
                    $KEY_CONTENT text not null
                )
            """)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("drop table if exists $TABLE_NAME")
        }
        onCreate(db)
    }

    fun getToDo(): List<Todo>{
        val result = mutableListOf<Todo>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME,null,null,
            null, null, null,null
        )

        if(cursor.moveToFirst()){
            val idIndex = cursor.getColumnIndex(KEY_ID)
            val idContent = cursor.getColumnIndex(KEY_CONTENT)
            do {
                result.add(
                    Todo(
                        cursor.getInt(idIndex),
                        cursor.getString(idContent)
                    )
                )
            } while(cursor.moveToNext())
        }
        cursor.close()

        return result
    }

    fun addToDo(content:String){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_CONTENT, content)
        database.insert(TABLE_NAME, null, contentValues)
        close()
    }

    fun delToDo(id:Int){
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tododb"
        const val TABLE_NAME = "todos"
        const val KEY_ID = "id"
        const val KEY_CONTENT = "content"
    }
}