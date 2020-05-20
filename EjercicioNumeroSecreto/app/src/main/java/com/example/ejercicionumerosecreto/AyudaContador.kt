package com.example.ejercicionumerosecreto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ayuda_contador.*
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class AyudaContador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_contador)

        imgBackContador.setOnClickListener{
            var contadorIntent = Intent(this, Contador::class.java)
            startActivity(contadorIntent)
        }

    }
}
