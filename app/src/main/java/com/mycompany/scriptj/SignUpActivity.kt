package com.mycompany.scriptj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val logInLink = findViewById<TextView>(R.id.txtDirLogInPage)
        logInLink.setOnClickListener {
            // Start the LoginActivity when the "Already have an account?" link is clicked
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    // You can also handle the click event using the XML attribute android:onClick="onLogInPageClick"
    // Make sure to remove android:onClick="onLogInPageClick" from the XML if you use this method
    // fun onLogInPageClick(view: View) {
    //     val intent = Intent(this, LoginActivity::class.java)
    //     startActivity(intent)
    // }
}






//package com.mycompany.scriptj
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class SignUpActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
//    }
//}
