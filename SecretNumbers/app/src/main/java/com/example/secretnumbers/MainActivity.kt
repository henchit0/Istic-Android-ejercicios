package com.example.secretnumbers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    var contador = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegister.setOnClickListener(){ registerUser() }
        btnRead.setOnClickListener(){ loginArchivo() }
        btnDelete.setOnClickListener(){ deleteFile() }

    }

    /*private fun Contar()
    {
        txtUser.text =  contador++.toString().toInt()
    }*/

    private fun mostrar(){
        txtMultiLine.text = txtUser.text
    }

    private fun registerUser()
    {
        try {
            val archivo = OutputStreamWriter(openFileOutput("registro.txt", Activity.MODE_APPEND))
            var userToFile = txtUser.text.toString()
            var passToFile = txtPassword.text.toString()
            //txtShow.text = "$userToFile=>$passToFile\n"
            archivo.write("$userToFile=>$passToFile\n")
            archivo.flush()
            archivo.close()
        } catch (ex: IOException) {
            Toast.makeText(this,"Error: ${ex.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteFile()
    {
        deleteFile("registro.txt")
    }

    private fun loginArchivo()
    {
        if (fileList().contains("registro.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("registro.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                var listado = StringBuilder()
                while (linea != null)
                {
                    listado.append("$linea\n")
                    linea = br.readLine()
                    /*val arrayDatos=linea.split("=>")
                    if(arrayDatos[0]=="pepito" && arrayDatos[1]=="1234")
                    {
                        break
                    }*/
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                txtMultiLine.setText(listado)
            } catch (ex: IOException) {
                Toast.makeText(this, "Eror: ${ex.message}",Toast.LENGTH_LONG).show()
            }

        }
    }

    /*private fun popUpDelete(){
        try {
            val btnBorrarDialogBuilder = AlertDialog.Builder(this@DatosContador) //Dialogo para eliminar listado
            btnBorrarDialogBuilder.setTitle("Eliminar")
            btnBorrarDialogBuilder.setIcon(R.mipmap.ic_launcher)
            btnBorrarDialogBuilder.setMessage("¿Esta seguro de eliminar el archivo del listado?")
            btnBorrarDialogBuilder.setCancelable(false)
            btnBorrarDialogBuilder.setPositiveButton("Si") {,->
                this.ttoas("Eliminando...")
                CargarListadoContador()
                finish()
            }
            btnBorrarDialogBuilder.setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Indico No...",Toast.LENGTH_SHORT).show()
            }
            btnBorrarDialogBuilder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(this, "Accion Cancelada..",Toast.LENGTH_SHORT).show()
            }
            val btnBorrarDialog = btnBorrarDialogBuilder.create()
            btnBorrarDialog.show()

            EliminarListado()
        }catch (e: IOException) {
            this.ttoas("Error al eliminar listado")
        }
    }*/

}