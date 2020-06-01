package com.example.ejercicionumerosecreto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipal : AppCompatActivity() {

    private lateinit var  detector : GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val btnAboutMe = findViewById<Button>(R.id.btnAboutMe)
        val btnNumeroSecreto = findViewById<Button>(R.id.btnNumeroSecreto)
        val btnContador = findViewById<Button>(R.id.btnContador)

        detector = GestureDetectorCompat(this, GestosComunes())


        btnAboutMe.setOnClickListener()
        {
            var perfilIntent = Intent(this, Perfil::class.java)
            startActivity(perfilIntent)
        }

        btnNumeroSecreto.setOnClickListener()
        {
            var numeroSecretoIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(numeroSecretoIntent)
        }

        btnContador.setOnClickListener()
        {
            var contadorIntent = Intent(this, Contador::class.java)
            startActivity(contadorIntent)
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
                        this@MenuPrincipal.onSwipeDerecha()
                    }
                    else
                    {       //swipe izquieda a derecha
                        this@MenuPrincipal.onSwipeIzquierda()
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
                        this@MenuPrincipal.onSwipeAbajo()
                    }
                    else
                    {       //swipe izquieda a derecha
                        this@MenuPrincipal.onSwipeArriba()
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
        //Toast.makeText(this,"Swipe arriba",Toast.LENGTH_LONG).show()
    }

    private fun onSwipeAbajo() {
        //Toast.makeText(this,"Swipe abajo",Toast.LENGTH_LONG).show()
    }

    private fun onSwipeIzquierda() {
        //Toast.makeText(this,"Swipe izquierda",Toast.LENGTH_LONG).show()
        var contadorIntent = Intent(this, Contador::class.java)
        startActivity(contadorIntent)
    }

    private fun onSwipeDerecha() {
        //Toast.makeText(this,"Swipe derecha",Toast.LENGTH_LONG).show()
        var numeroSecretoIntent = Intent(this, NumeroSecreto::class.java)
        startActivity(numeroSecretoIntent)
    }
}
