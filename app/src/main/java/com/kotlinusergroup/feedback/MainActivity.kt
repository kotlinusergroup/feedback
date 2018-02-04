package com.kotlinusergroup.feedback

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase initialization
        database = FirebaseDatabase.getInstance()
        //database reference
        var databaseReference = database.getReference("message")

        sendButton.setOnClickListener {

            if (nameEditText.text.isEmpty() && messageEditText.text.isEmpty()) {

                //Write to firebase database
                databaseReference.push().setValue(Feedback(nameEditText.text.toString(),messageEditText.text.toString()))

                //Clearing the text
                nameEditText.text.clear()
                messageEditText.text.clear()

                //Message after successful write
                Toast.makeText(this,getString(R.string.thank_message),Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,getString(R.string.error_message),Toast.LENGTH_LONG).show()
            }
        }
    }
}
