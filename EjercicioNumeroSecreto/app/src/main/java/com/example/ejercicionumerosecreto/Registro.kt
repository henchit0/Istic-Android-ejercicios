package com.example.ejercicionumerosecreto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnRegister.setOnClickListener{
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
            var backLoginIntent = Intent(this,MainActivity::class.java)
            startActivity(backLoginIntent)
        }

    }
}
