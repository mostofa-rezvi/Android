package com.example.foodzilla3.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.foodzilla3.MainActivity
import com.example.foodzilla3.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception


class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtfrgt: TextView
    lateinit var txtCreate: TextView
    var phoneNoInput: String? = null
    var passInput: String? = null
    var userID: String? = ""
    lateinit var progBar:ProgressBar
    lateinit var auth: FirebaseAuth


    override fun onStart() {
        super.onStart()
        println("Login activity")
        if (checkLoggedInState()) {
            intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail= findViewById(R.id.username)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnlogin)
        txtfrgt = findViewById(R.id.txtforgotPassword)
        txtCreate = findViewById(R.id.txtCreate)
progBar=findViewById(R.id.loading)

        auth = FirebaseAuth.getInstance()

        println("Login 1")

        btnLogin.setOnClickListener{
            println("button press")
            val mobileNo = etEmail?.text.toString()
            val pass = etPassword?.text.toString()
            if (mobileNo.isEmpty() && pass.isEmpty()) {
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
//               progBar.visibility= View.VISIBLE
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        auth.signInWithEmailAndPassword(mobileNo, pass).await()
                        if (checkLoggedInState()) {
                            intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            println("Logged in state ${checkLoggedInState()}")
                        }
                    } catch (e: Exception) {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@LoginActivity,
//                                e.message,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
                        println(e.message)
                    }
                }
//                progBar.visibility=View.INVISIBLE
                }

            }
            txtCreate.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            txtfrgt.setOnClickListener {
                Intent(this@LoginActivity, ForgetPasswordActivity::class.java).apply {
                startActivity(this)
                }
            }
        }


    fun checkLoggedInState(): Boolean {
        println(" login"+auth.currentUser?.email)
        return auth.currentUser != null
    }
}