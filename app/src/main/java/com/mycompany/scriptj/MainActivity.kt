
package com.mycompany.scriptj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)
        installSplashScreen()

        setContentView(R.layout.activity_login)

        val signUpLink = findViewById<TextView>(R.id.txtDirSignUpPage)
        signUpLink.setOnClickListener {
            // Start the SignUpActivity when the "Don't have an account?" link is clicked
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}