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

    companion object {
        const val EXTRA_INDEX = "INDEX1"
        const val EXTRA_VALUE = "VALUE1"
        const val REQUEST_CODE = 1
        const val RESUTL_CODE = 2
    }

    private lateinit var adapter:RecyclerAdapter;

    private var listItem = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



       adapter = RecyclerAdapter(listItem){
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(EXTRA_INDEX, it)
            intent.putExtra(EXTRA_VALUE, listItem[it])
            startActivityForResult(intent, REQUEST_CODE)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editText)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {

            if(editText.text.toString()!="") {
                listItem.add(editText.text.toString())
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
                listItem[index] = result
                adapter.notifyItemChanged(index)
            }

        }
    }


}