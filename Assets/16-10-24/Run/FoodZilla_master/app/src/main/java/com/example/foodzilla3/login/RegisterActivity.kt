package com.example.foodzilla3.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodzilla3.MainActivity
import com.example.foodzilla3.R
import com.example.foodzilla3.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

lateinit var name: EditText
lateinit var mobile_number: EditText
lateinit var password: EditText
lateinit var confirm_password: EditText
lateinit var address: EditText
lateinit var email: EditText
lateinit var btnsubmit: Button
lateinit var auth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
     private var userCollection= Firebase.firestore.collection("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        name = findViewById(R.id.editTextTextPersonName)
        mobile_number = findViewById(R.id.editTextPhone)
        password = findViewById(R.id.editTextTextPassword)
        confirm_password = findViewById(R.id.editTextTextPassword2)
        email = findViewById(R.id.editTextTextEmailAddress)
        address = findViewById(R.id.editTextTextPostalAddress)
        btnsubmit = findViewById(R.id.btnSubmit)
        auth = FirebaseAuth.getInstance()
        btnsubmit.setOnClickListener {
            var vname = name.text.toString()
            var vphoneNo = mobile_number.text.toString()
            var vpass = password.text.toString()
            var vConPass = confirm_password.text.toString()
            var vmail = email.text.toString()
            var vaddress = address.text.toString()

            if (vname != null) {
                if (vphoneNo.length == 10) {
                    if (vpass == vConPass) {

                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                auth.createUserWithEmailAndPassword(vmail, vpass).await()
                                var user= Users(vname,vmail,vphoneNo,vaddress)
                                withContext(Dispatchers.IO){
                                    var currentUser= auth.currentUser?.uid
                                            userCollection.document("$currentUser").set(user).addOnSuccessListener{
                                                Log.d("Data sent to firebase","Saved Succesfully" )
                                            }
                                                .addOnFailureListener {
                                                    Log.d("Data sent to firebase","Not Saved Succesfully" )
                                                }.await()

                                }
                                intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                startActivity(intent)
                            } catch (e: java.lang.Exception) {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        e.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        }

                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Password Do not match",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Enter correct No", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this@RegisterActivity, "user name Empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

//    fun jsonReqSender(
//        name: String,
//        mobNo: String,
//        pass: String,
//        address: String,
//        email: String
//    ): Boolean {
//        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
//        var success = false
//        val url = "http://13.235.250.119/v2/register/fetch_result"
//        var jsonParams = JSONObject()
//        jsonParams.put("name", name)
//        jsonParams.put("mobile_number", mobNo)
//        jsonParams.put("password", pass)
//        jsonParams.put("address", address)
//        jsonParams.put("email", email)
//        val jsonObject =
//            object : JsonObjectRequest(
//                Method.POST, url, jsonParams, Response.Listener {
//                    try {
//                        println("Register is $it")
//                        val userJson = it.getJSONObject("data")
//                        success = userJson.getBoolean("success")
//                        if (!success) {
//                            var error = it.getJSONObject("errorMessage")
//                            Toast.makeText(this@RegisterActivity, "$error", Toast.LENGTH_SHORT)
//                                .show()
//                        } else {
//                            Toast.makeText(this@RegisterActivity, "Registered", Toast.LENGTH_SHORT)
//                            var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//                            intent.putExtra("PhoneNo", mobNo)
//                            intent.putExtra("Password", pass)
//                            startActivity(intent)
//                            finish()
//                        }
//                        println("1 " + success)
//
//                    } catch (e: Exception) {
//                        Toast.makeText(
//                            this@RegisterActivity,
//                            "Some error Occur on sending request",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                },
//                Response.ErrorListener {
//                    println("Error is $it")
//                }) {
//                override fun getHeaders(): MutableMap<String, String> {
//                    val headers = HashMap<String, String>()
//                    headers["Content-type"] = "application/json"
//                    headers["token"] = "ee1acc36c8a460"
//                    return headers
//                }
//            }
//        queue.add(jsonObject)
//        println("out")
//        return success
//    }
