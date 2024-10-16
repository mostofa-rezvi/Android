package com.example.foodzilla3.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodzilla3.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


lateinit var phoneNo2: EditText
lateinit var email2: EditText
lateinit var newPass: EditText
lateinit var btnNext: Button

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        email2 = findViewById(R.id.etEmailAddress2)
        btnNext = findViewById(R.id.button2)



        btnNext.setOnClickListener {
            var emailnum = email2.text.toString()
            Log.d("Button Press","Pressed")
            retriveUserData(emailnum)

        }
    }

private fun retriveUserData(email:String)= CoroutineScope(Dispatchers.IO).launch{
    try {

        var auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
        Toast.makeText(this@ForgetPasswordActivity,"Email Sent",Toast.LENGTH_LONG).show()
        Intent(this@ForgetPasswordActivity, LoginActivity::class.java).apply {
            startActivity(this)
        }


    }catch (e:Exception){
        withContext(Dispatchers.Main){
            Toast.makeText(this@ForgetPasswordActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}
}