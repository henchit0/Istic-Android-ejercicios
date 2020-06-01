package com.example.ejercicionumerosecreto


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnLogin.setOnClickListener()
        {
            var mainMenuIntent = Intent(this, MenuPrincipal::class.java)
            startActivity(mainMenuIntent)
        }

        btnSignUp.setOnClickListener()
        {
            var registerIntent = Intent(this, Registro::class.java)
            startActivity(registerIntent)
        }

        //var menuMainIntent = Intent(this,MenuPrincipal::class.java)
        //startActivity(menuMainIntent)

    }






}
