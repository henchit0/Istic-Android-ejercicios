package com.example.ejercicionumerosecreto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class NumeroSecreto : AppCompatActivity() {

    var numeroSecreto = 0
    var contadorVidas = 5
    var contadorIntentos = 0

    fun Iniciar(){
        numeroSecreto = (Math.random() * 100).toInt();
        contadorVidas = 5
        contadorIntentos = 0
        //numeroSecreto = 4;//(Math.random() * 100).toInt();
        txtIngresar.isEnabled = true
        txtIngresar.visibility = View.VISIBLE
        txtIngresar.setHint("Ingresa tu numero!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numero_secreto)

        btnComenzar.setOnClickListener{
            this.Iniciar();
        }

        btnAdivinar.setOnClickListener{
            if (txtIngresar.text.toString().toInt() > 100){
                Toast.makeText(this, "El numero tiene que ser menor a 100.", Toast.LENGTH_LONG).show()
                txtIngresar.clearComposingText()
            }
            else if (txtIngresar.text.toString().toInt() < 1)
            {
                Toast.makeText(this, "El numero tiene que ser mayor a 0.", Toast.LENGTH_LONG).show()
            }
            else if (contadorVidas > 0)
            {
                if (txtIngresar.text.toString().toInt() < numeroSecreto)
                {
                    Toast.makeText(this, "Mandale mas.", Toast.LENGTH_LONG).show()
                    contadorVidas --
                    contadorIntentos ++
                }
                else if (txtIngresar.text.toString().toInt() > numeroSecreto)
                {
                    Toast.makeText(this, "Te pasaste un toque.", Toast.LENGTH_LONG).show()
                    contadorVidas --
                    contadorIntentos ++

                }
                else if (txtIngresar.text.toString().toInt() == numeroSecreto)
                {
                    Toast.makeText(this, "Adivinaste!!!", Toast.LENGTH_LONG).show()
                    contadorVidas = 5
                }
            }
            else if (contadorVidas == 0)
            {
                Toast.makeText(this, "Perdiste!!! :( El numero era " + numeroSecreto, Toast.LENGTH_LONG).show()
            }
        }

    }
}
