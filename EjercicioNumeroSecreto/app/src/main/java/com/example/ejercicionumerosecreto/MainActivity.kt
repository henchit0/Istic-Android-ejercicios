package com.example.ejercicionumerosecreto


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.security.AuthProvider


public class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val AUTH_REQUEST_CODE = 2000
    private var user : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val txtUser = findViewById<TextView>(R.id.txtUser)
        val txtPass = findViewById<TextView>(R.id.txtPass)

        btnLogin.setOnClickListener()
        {
            logon()
        }

        btnSignUp.setOnClickListener()
        {
            var registerIntent = Intent(this, Registro::class.java)
            startActivity(registerIntent)
        }
    }

    private fun logon()
    {
        var email = this.txtUser.text.toString().trim()
        var pass = this.txtPass.text.toString().trim()
        auth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Mail no registrado",Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null)
        {
            startActivity(Intent(this,MenuPrincipal::class.java))
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }



}
