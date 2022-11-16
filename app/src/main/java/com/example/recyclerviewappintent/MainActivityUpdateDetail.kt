package com.example.recyclerviewappintent

import android.content.Context
import android.content.Intent
import android.icu.text.Edits
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivityUpdateDetail : AppCompatActivity() {

    private val phoneBookDatabase by lazy {
        PhoneBookDatabase.getDatabase(this).userDao()
    }

    private fun transferAnotherActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_update_detail)
        setTitle("Editing a contact")

        val id = intent.getLongExtra(MainActivity.USER_ENTITY_ID, -1)


        val editTextFN = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLN = findViewById<EditText>(R.id.editTextLastName)
        val editTextB = findViewById<EditText>(R.id.editTextDate)
        val editTextP = findViewById<EditText>(R.id.editTextPhone)

        var userEntity: UserEntity = UserEntity();
        var userEntityEdit: UserEntity = UserEntity();

        if(id > 0){
            userEntityEdit = phoneBookDatabase.getById(id)
            userEntity = userEntityEdit
            editTextFN.setText(userEntityEdit.firstName)
            editTextLN.setText(userEntityEdit.lastName)
            editTextB.setText(userEntityEdit.birthday)
            editTextP.setText(userEntityEdit.phone)
        }

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)

        buttonSave.setOnClickListener {

            userEntity.firstName = editTextFN.text.toString()
            userEntity.lastName = editTextLN.text.toString()
            userEntity.birthday = editTextB.text.toString()
            userEntity.phone = editTextP.text.toString()

            if (id < 1){
                phoneBookDatabase.insert(userEntity)
                transferAnotherActivity()

            } else {
                phoneBookDatabase.update(userEntity)
                transferAnotherActivity()
            }
        }

        buttonCancel.setOnClickListener {
//            if (id < 1){
//                phoneBookDatabase.delete(userEntity)
//            }
//            else{
            if(id > 0){
                userEntity = userEntityEdit
                phoneBookDatabase.update(userEntity)
            }
            transferAnotherActivity()
        }
    }
}