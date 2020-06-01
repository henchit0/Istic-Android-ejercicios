package com.example.ejercicionumerosecreto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_ayuda_num_sec.*
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class AyudaNumeroSecreto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_num_sec)
        val imgBackNumeroSecreto = findViewById<ImageView>(R.id.imgBackNumeroSecreto)

        imgBackNumeroSecreto.setOnClickListener()
        {
            val numeroScretoIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(numeroScretoIntent)
        }

    }
}
