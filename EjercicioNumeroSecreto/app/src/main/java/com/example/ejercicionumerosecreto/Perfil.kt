package com.example.ejercicionumerosecreto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_perfil.*

class Perfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        imgPerfil.bringToFront()
        val btnVolverMenuPrincipal = findViewById<Button>(R.id.btnVolverMenuPrincipal)

        btnVolverMenuPrincipal.setOnClickListener()
        {
            var backMainMenuIntent = Intent(this,MenuPrincipal::class.java)
            startActivity(backMainMenuIntent)
        }

    }
}
