package com.example.ejercicionumerosecreto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class NumeroSecreto : AppCompatActivity() {

    var numeroSecreto = 0
    var contadorIntentos = 0
    private lateinit var  detector : GestureDetectorCompat

    fun Iniciar(){
        numeroSecreto = (Math.random() * 100).toInt();
        contadorIntentos = 0
        txtIngresar.isEnabled = true
        txtIngresar.visibility = View.VISIBLE
        txtIngresar.setHint("Ingresa tu numero!")
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numero_secreto)

        detector = GestureDetectorCompat(this, GestosComunes())

        btnComenzar.setOnClickListener{
            this.Iniciar()
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
            else if (contadorIntentos < 5)
            {
                when {
                    txtIngresar.text.toString().toInt() < numeroSecreto -> {
                        Toast.makeText(this, "Mandale mas.", Toast.LENGTH_LONG).show()
                        contadorIntentos ++
                        this.Perdiste()
                    }
                    txtIngresar.text.toString().toInt() > numeroSecreto -> {
                        Toast.makeText(this, "Te pasaste un toque.", Toast.LENGTH_LONG).show()
                        contadorIntentos ++
                        this.Perdiste()
                    }
                    txtIngresar.text.toString().toInt() == numeroSecreto -> {
                        Toast.makeText(this, "Adivinaste!!!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun Perdiste()
    {
        if (contadorIntentos == 5)
        {
            Toast.makeText(this, "Perdiste!!! :( El numero era " + numeroSecreto, Toast.LENGTH_LONG).show()
            txtIngresar.text.clear()
            txtIngresar.isEnabled = false
            txtIngresar.visibility = View.INVISIBLE
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event))
        {
            true
        }
        else
        {
            super.onTouchEvent(event)
        }

    }

    inner class GestosComunes : GestureDetector.SimpleOnGestureListener()
    {

        private val LIMETE_SWIPE = 100
        private val LIMETE_SWIPE_VELOCIDAD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if (Math.abs(diffX) > Math.abs(diffY))
            {       // Esto seria un swipe de derecha a izq o viceversa
                if (Math.abs(diffX) > LIMETE_SWIPE && Math.abs(velocityX) > LIMETE_SWIPE_VELOCIDAD)
                {
                    if (diffX > 0)
                    {       // Swipe derecha a izquierda
                        this@NumeroSecreto.onSwipeDerecha()
                    }
                    else
                    {       //swipe izquieda a derecha
                        this@NumeroSecreto.onSwipeIzquierda()
                    }
                    true
                }
                else
                {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
            else
            {       // Esto seria un swioe de arrriba a abajo o viceversa
                if (Math.abs(diffY) > LIMETE_SWIPE && Math.abs(velocityY) > LIMETE_SWIPE_VELOCIDAD)
                {
                    if (diffY > 0)
                    {       // Swipe derecha a izquierda
                        this@NumeroSecreto.onSwipeAbajo()
                    }
                    else
                    {       //swipe izquieda a derecha
                        this@NumeroSecreto.onSwipeArriba()
                    }
                    true
                }
                else
                {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
        }
    }

    private fun onSwipeArriba() {
        Toast.makeText(this,"Swipe arriba",Toast.LENGTH_LONG).show()
    }

    private fun onSwipeAbajo() {
        Toast.makeText(this,"Swipe abajo",Toast.LENGTH_LONG).show()    }

    private fun onSwipeIzquierda() {
        //Toast.makeText(this,"Swipe izquierda",Toast.LENGTH_LONG).show()
        var mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    private fun onSwipeDerecha() {
        //Toast.makeText(this,"Swipe derecha",Toast.LENGTH_LONG).show()
        var contadorIntent = Intent(this, Contador::class.java)
        startActivity(contadorIntent)
    }
}
