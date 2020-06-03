package com.example.ejercicionumerosecreto


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val txtUser = findViewById<TextView>(R.id.txtUser)
        val txtPass = findViewById<TextView>(R.id.txtPass)

        btnLogin.setOnClickListener()
        {
            var userToCheck = txtUser.text.toString()
            var passToCheck = txtPass.text.toString()

            if (checkEmptyFiedls(userToCheck,passToCheck))
            {
                when {
                    checkLogin(userToCheck,passToCheck) == "" -> {
                        Toast.makeText(this,"No estas registrado",Toast.LENGTH_LONG).show()
                    }
                    checkLogin(userToCheck,passToCheck) == "userOk" -> {
                        Toast.makeText(this,"Contraseña erronea",Toast.LENGTH_LONG).show()
                    }
                    checkLogin(userToCheck,passToCheck) == "loginOk" -> {
                        Toast.makeText(this,"Login exitoso!",Toast.LENGTH_LONG).show()
                        var mainMenuIntent = Intent(this, MenuPrincipal::class.java)
                        startActivity(mainMenuIntent)
                    }
                }
            }
            else
            {
                Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show()
            }

        }

        btnSignUp.setOnClickListener()
        {
            var registerIntent = Intent(this, Registro::class.java)
            startActivity(registerIntent)
        }
    }

    private fun checkEmptyFiedls(user:String,pass:String) : Boolean
    {
        var flag = true
        if (user == "" || pass == "") flag = false
        return flag
    }

    private fun checkLogin(user:String,pass:String) :String
    {
        var flagUser = ""
        if (fileList().contains("registro.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("registro.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                var listado = StringBuilder()
                while (linea != null)
                {
                    val arrayDatos=linea.split("=>")
                    if (arrayDatos[0] == txtUser.text.toString())
                    {
                        flagUser = "userOk"
                        if (arrayDatos[1] == txtPass.text.toString())
                        {
                            flagUser = "loginOk"
                            break
                        }
                    }
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
            } catch (ex: IOException) {
                Toast.makeText(this, "Error: ${ex.message}", Toast.LENGTH_LONG).show()
            }
        }
        return flagUser
    }
}
