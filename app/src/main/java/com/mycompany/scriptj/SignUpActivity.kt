package com.mycompany.scriptj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var txtInputEmailSignUp: TextInputEditText
    private lateinit var txtInputPassSignUp: TextInputEditText
    private lateinit var btnSignUp: Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        txtInputEmailSignUp = findViewById(R.id.txtInput_Email_SignUp)
        txtInputPassSignUp = findViewById(R.id.txtInput_Pass_SignUp)
        btnSignUp = findViewById(R.id.btnSignUp)

        // Handle sign-up button click
        btnSignUp.setOnClickListener {
            val email = txtInputEmailSignUp.text.toString().trim()
            val password = txtInputPassSignUp.text.toString().trim()

            // Check if email and password are not empty
            if (email.isEmpty() || password.isEmpty()) {
                // Display a warning message if email or password is empty
                displayErrorMessage("Please enter both email and password.")
                return@setOnClickListener
            }

            // Check if the email is valid
            if (!isValidEmail(email)) {
                // Display a warning message if the email is not valid
                displayErrorMessage("Invalid email address.")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up successful. You can navigate to the next activity or perform other actions here
                        val intent = Intent(this, LogInActivity::class.java)
                        startActivity(intent)
                        finish() // Finish the SignUpActivity to prevent going back with the back button
                    } else {
                        // If sign up fails, display a message to the user.
                        // You can handle different failure scenarios based on task.exception
                        // If the email is already in use, you can catch FirebaseAuthUserCollisionException and display a specific error message.
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            // Handle user already exists
                            val errorMessage = "User with this email already exists."
                            displayErrorMessage(errorMessage)
                        } else {
                            // Handle other exceptions
                            val errorMessage = "Use 6 characters for the password or provide a valid email address."
                            displayErrorMessage(errorMessage)
                        }
                    }
                }
        }

        val logInLink = findViewById<TextView>(R.id.txtDirLogInPage)
        logInLink.setOnClickListener {
            // Start the LoginActivity when the "Already have an account?" link is clicked
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to display error messages
    private fun displayErrorMessage(message: String) {
        // You can implement your logic to display the error message to the user
        // For simplicity, we'll just show a toast message here
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Function to check if the email is valid
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }
}