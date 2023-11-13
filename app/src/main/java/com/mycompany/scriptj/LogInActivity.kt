
package com.mycompany.scriptj

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mycompany.scriptj.databinding.ActivityLoginBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install Splash Screen
        installSplashScreen()

        // Initialize Firebase Authentication
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // Check if the user is already signed in
        if (auth.currentUser != null) {
            // User is already signed in, redirect to NavigationActivity
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
            return
        }

        // Set layout and other code
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the login button
        val loginButton = binding.btnLogIn
        loginButton.setOnClickListener {
            // Call a function to handle login
            handleLogin()
        }

        // Set click listener for the "Don't have an account?" link
        val signUpLink = binding.txtDirSignUpPage
        signUpLink.setOnClickListener {
            // Start the SignUpActivity when the link is clicked
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for the "Forgot Password" link
        val forgotPasswordLink = binding.txtForgotPassword
        forgotPasswordLink.setOnClickListener {
            // Start the ForgotPassActivity when the link is clicked
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to handle the login process
    private fun handleLogin() {
        val email = findViewById<EditText>(R.id.txtInput_Email_Login).text.toString()
        val password = findViewById<EditText>(R.id.txtInput_Pass_Login).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Authenticate the user with Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login successful, navigate to NavigationActivity
                        val intent = Intent(this, NavigationActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign-in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed. Wrong Email or Password.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            // Show a warning if email or password is empty
            Toast.makeText(
                baseContext, "Please enter both email and password.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}






















//package com.mycompany.scriptj
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
//import com.google.firebase.FirebaseApp
//import com.google.firebase.auth.FirebaseAuth
//
//class LogInActivity : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        // Install Splash Screen
//        installSplashScreen()
//
//
//        // Initialize Firebase Authentication
//        FirebaseApp.initializeApp(this)
//        auth = FirebaseAuth.getInstance()
//
//
//        // Check if the user is already signed in
//        if (auth.currentUser != null) {
//            // User is already signed in, redirect to MainActivity
//            startActivity(Intent(this, NavigationActivity::class.java))
//            finish()
//            return
//        }
//
//        // Set layout and other code
//        setContentView(R.layout.activity_login)
//
//        // Set click listener for the login button
//        val loginButton = findViewById<Button>(R.id.btnLogIn)
//        loginButton.setOnClickListener {
//            // Call a function to handle login
//            handleLogin()
//        }
//
//        // Add any additional logic or UI setup for your login activity here
//        val signUpLink = findViewById<TextView>(R.id.txtDirSignUpPage)
//        signUpLink.setOnClickListener {
//            // Start the SignUpActivity when the "Don't have an account?" link is clicked
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }
//
//
//        val forgotPasswordLink = findViewById<TextView>(R.id.txtForgotPassword)
//        forgotPasswordLink.setOnClickListener {
//            // Start the ForgotPassActivity when the "Forgot Password" link is clicked
//            val intent = Intent(this, ForgotPassActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun handleLogin() {
//        val email = findViewById<EditText>(R.id.txtInput_Email_Login).text.toString()
//        val password = findViewById<EditText>(R.id.txtInput_Pass_Login).text.toString()
//
//        if (email.isNotEmpty() && password.isNotEmpty()) {
//            // Authenticate the user with Firebase
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Login successful, navigate to the next screen or perform any other action
//                        val intent = Intent(this, NavigationActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    } else {
//                        // If sign-in fails, display a message to the user.
//                        Toast.makeText(
//                            baseContext, "Authentication failed. Wrong Email or Password.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//        } else {
//            // Show a warning if email or password is empty
//            Toast.makeText(
//                baseContext, "Please enter both email and password.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//}

