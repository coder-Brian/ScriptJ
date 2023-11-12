package com.mycompany.scriptj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fpassword)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Reset Password Button
        val resetPasswordButton: Button = findViewById(R.id.btnResetPassword)
        resetPasswordButton.setOnClickListener {
            // Retrieve email from the EditText
            val emailEditText = findViewById<EditText>(R.id.txtInput_Email_ForgotPassword)
            val email = emailEditText.text.toString()

            if (email.isNotEmpty()) {
                // Check if the email is registered
                auth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Retrieve sign-in methods for the email
                            val signInMethods = task.result?.signInMethods
                            if (signInMethods == null || signInMethods.isEmpty()) {
                                // Email is not registered
                                Toast.makeText(
                                    baseContext, "Email is not registered.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Email is registered, send password reset email
                                auth.sendPasswordResetEmail(email)
                                    .addOnCompleteListener { resetTask ->
                                        if (resetTask.isSuccessful) {
                                            // Password reset email sent successfully
                                            Toast.makeText(
                                                baseContext, "Password reset email sent.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            // Failed to send password reset email
                                            Toast.makeText(
                                                baseContext, "Failed to send password reset email.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        } else {
                            // Failed to fetch sign-in methods
                            Toast.makeText(
                                baseContext, "Failed to check email registration.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // Show a warning if the email is empty
                Toast.makeText(
                    baseContext, "Please enter your email.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Back to Login link
        val backToLoginTextView = findViewById<TextView>(R.id.txtBackToLogin)
        backToLoginTextView.setOnClickListener {
            // Navigate back to the Login screen
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}