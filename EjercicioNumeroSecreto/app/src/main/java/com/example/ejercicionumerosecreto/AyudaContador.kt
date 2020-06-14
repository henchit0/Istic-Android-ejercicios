package com.example.ejercicionumerosecreto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_ayuda_contador.*
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class AyudaContador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_contador)
        val imgBackContador = findViewById<ImageView>(R.id.imgBackContador)

        imgBackContador.setOnClickListener()
        {
            var contadorIntent = Intent(this, Contador::class.java)
            startActivity(contadorIntent)
        }

    }
}
