package com.example.ejercicionumerosecreto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicionumerosecreto.DataModel.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter


class Registro : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = FirebaseAuth.getInstance()

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
        val txtPassword = findViewById<TextView>(R.id.txtPassword)
        val txtConfirmPass = findViewById<TextView>(R.id.txtConfirmPass)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)

        btnRegister.setOnClickListener()
        {
            var userToFile = txtUsuario.text.toString().trim()
            var passToFile = txtPassword.text.toString().trim()
            var confirmPassToFile = txtConfirmPass.text.toString().trim()
            var emailToFile = txtEmail.text.toString().trim()

            if (validateForm(userToFile,passToFile,confirmPassToFile ,emailToFile))
            {
                registerUser(emailToFile,passToFile)
                Toast.makeText(this,"Registro exitoso!",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun registerUser(pass:String,email:String)
    {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun saveUserData(user: String,email: String)
    {
        try {
            val dbRef = DBReference()
            val userObj = User(user,email)
            dbRef.push().setValue(userObj).addOnCompleteListener {
                Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
            }
        }
        catch (ex: Throwable) {
            Toast.makeText(this,"Error: ${ex.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun validateForm(user:String,pass:String,passConfirm:String,email:String) : Boolean
    {
        var flag = true
        when {
            user.isEmpty() -> {
                this.txtUsuario.error = "Debes ingresar un usuario"
                this.txtUsuario.requestFocus()
                flag = false
            }
            (pass.length in 1..5)  -> {
                this.txtPassword.error = "Debe tener al menos 6 caracteres"
                this.txtPassword.requestFocus()
                flag = false
            }
            pass.isEmpty() -> {
                this.txtPassword.error = "Debes ingresar una contraseña"
                this.txtPassword.requestFocus()
                flag = false
            }
            passConfirm.isEmpty() -> {
                this.txtConfirmPass.error = "Debes ingresar una contraseña"
                this.txtConfirmPass.requestFocus()
                flag = false
            }
            pass != passConfirm -> {
                this.txtPassword.error = "Las contraseñas deben coincidir"
                this.txtConfirmPass.error = "Las contraseñas deben coincidir"
                this.txtPassword.requestFocus()
                flag = false
            }
            email.isEmpty() -> {
                this.txtEmail.error = "Debes ingresar un email"
                this.txtEmail.requestFocus()
                flag = false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                this.txtEmail.error = "Ingresa un mail valido"
                this.txtEmail.requestFocus()
                flag = false
            }
        }
        return flag
    }

    private fun DBReference() : DatabaseReference
    {
        val dbRef =  FirebaseDatabase.getInstance().getReference("users")
        return dbRef
    }

}


