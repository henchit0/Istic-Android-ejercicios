package com.example.ejercicionumerosecreto

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_numero_secreto.*
import kotlinx.android.synthetic.main.activity_registro.*
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
        val txtPassword = findViewById<TextView>(R.id.txtPassword)
        val txtConfirmPass = findViewById<TextView>(R.id.txtConfirmPass)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)

        btnRegister.setOnClickListener()
        {
            var userToFile = txtUsuario.text.toString()
            var passToFile = txtPassword.text.toString()
            var confirmPassToFile = txtConfirmPass.text.toString()
            var emailToFile = txtEmail.text.toString()
            if (checkEmptyFiedls(userToFile,passToFile,confirmPassToFile,emailToFile))
            {
                if (doubleCheckPassword(passToFile,confirmPassToFile))
                {
                    if (checkEmail(emailToFile))
                    {
                        if (checkUser(userToFile))
                        {
                            registerUser(userToFile, passToFile, emailToFile)
                        }
                        else
                        {
                            Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Email ya registrado",Toast.LENGTH_LONG).show()
                    }
                }
                else
                {
                    Toast.makeText(this,"Las contraseñas deben coincidir",Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun registerUser(user:String,pass:String,email:String)
    {
        try {
            val archivo = OutputStreamWriter(openFileOutput("registro.txt", Activity.MODE_APPEND))
            //txtShow.text = "$userToFile=>$passToFile\n"
            archivo.write("$user=>$pass=>$email\n")
            archivo.flush()
            archivo.close()
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
            var backLoginIntent = Intent(this,MainActivity::class.java)
            startActivity(backLoginIntent)
        } catch (ex: IOException) {
            Toast.makeText(this,"Error: ${ex.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun checkUser(user:String) :Boolean
    {
        var flagUser = true
        if (fileList().contains("registro.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("registro.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                while (linea != null)
                {
                    val arrayDatos=linea.split("=>")
                    if (arrayDatos[0] == user)
                    {
                        flagUser = false
                        break
                    }
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
            } catch (ex: IOException) {
                Toast.makeText(this, "Error: ${ex.message}",Toast.LENGTH_LONG).show()
            }
        }
        return flagUser
    }

    private fun checkEmail(mail:String) :Boolean
    {
        var flagUser = true
        if (fileList().contains("registro.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("registro.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                var listado = StringBuilder()
                while (linea != null)
                {
                    val arrayDatos=linea.split("=>")
                    if (arrayDatos[2] == mail)
                    {
                        flagUser = false
                        break
                    }
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
            } catch (ex: IOException) {
                Toast.makeText(this, "Error: ${ex.message}",Toast.LENGTH_LONG).show()
            }
        }
        return flagUser
    }

    private fun checkEmptyFiedls(user:String,pass:String,passConfirm:String,email:String) : Boolean
    {
        var flag = true
        if (user == "" || pass == "" || passConfirm == "" || email == "") flag = false
        return flag
    }

    private fun doubleCheckPassword(pass:String,passC:String) : Boolean
    {
        return pass == passC
    }
}

