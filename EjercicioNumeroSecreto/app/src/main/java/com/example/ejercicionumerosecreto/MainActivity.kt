package com.example.ejercicionumerosecreto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnComenzar.setOnClickListener{
            txtMensaje.text = (Math.random() * 100).toInt().toString();

        }

        btnAdivinar.setOnClickListener{

            var numeroIngresado = txtIngresar.toString().toInt();
            if (numeroIngresado == txtMensaje.toString().toInt())
            {
                Toast.makeText(this, "Sos un crack", Toast.LENGTH_SHORT).show()
            }
        }
        /*var contador:Int = 0;
        btnLogin.setOnClickListener{
            contador ++;
            txtMostrar.text = contador.toString();
        }*/

    }
}
