package com.example.recyclerviewappintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity2 : AppCompatActivity() {

    companion object{
        const val RESULT_INDEX = "RESULT_INDEX"
        const val RESULT_VALUE = "RESULT_VALUE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val index = intent.getIntExtra(MainActivity.EXTRA_INDEX,-1)
        val value = intent.getStringExtra(MainActivity.EXTRA_VALUE)
        val editText = findViewById<EditText>(R.id.editText)
        editText.setText(value)

        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)

        buttonUpdate.setOnClickListener {
            val intent1 = Intent()


            intent1.putExtra(RESULT_INDEX, index)
            intent1.putExtra(RESULT_VALUE, editText.text.toString())

            setResult(MainActivity.RESUTL_CODE, intent1)

            finish()
        }

    }
}