package com.example.recyclerviewappintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.RcsUceAdapter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val dBHelper = DBHelper(this)

    companion object {
        const val EXTRA_INDEX = "INDEX1"
        const val EXTRA_VALUE = "VALUE1"
        const val REQUEST_CODE = 1
        const val RESUTL_CODE = 2
    }

    private lateinit var adapter:RecyclerAdapter;

    private var listItem = mutableListOf<Todo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (item in dBHelper.getToDo()){
            listItem.add(Todo(item.id, item.content))
        }

       adapter = RecyclerAdapter(listItem, { updateId ->
               val intent = Intent(this, MainActivity2::class.java)
               intent.putExtra(EXTRA_INDEX, updateId)
               intent.putExtra(EXTRA_VALUE, listItem[updateId].content)
               startActivityForResult(intent, REQUEST_CODE)
       }, { delId, delIdDb ->
               listItem.removeAt(delId)
                dBHelper.delToDo(delIdDb)
               adapter.notifyItemRemoved(delId)
           })


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editText)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {

            if(editText.text.toString()!="") {
//                val todo = Todo()
                listItem.add(Todo(listItem.lastIndex+1,editText.text.toString()))
                dBHelper.addToDo(editText.text.toString())
                adapter.notifyItemInserted(listItem.lastIndex)
                editText.text = null
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESUTL_CODE && requestCode == REQUEST_CODE && data!=null){

            val result = data.getStringExtra(MainActivity2.RESULT_VALUE)
            val index = data.getIntExtra(MainActivity2.RESULT_INDEX,-1)

            if (index >-1 && result != null){
                listItem[index].content = result
                adapter.notifyItemChanged(index)
            }

        }
    }


}