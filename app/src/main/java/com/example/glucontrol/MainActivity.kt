package com.example.glucontrol

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creo base datos SQLite para guardar registros localmente
        val myDB = openOrCreateDatabase("glucontrol.db", MODE_PRIVATE, null)
        //creo tablas necesarias user (usuario que se registra) y control (registra fecha y valor de la toma de azucar)
        myDB.execSQL(
            "CREATE TABLE IF NOT EXISTS user (name VARCHAR(200))"
        );
        myDB.execSQL(
            "CREATE TABLE IF NOT EXISTS control (fecha VARCHAR(200), valor INTEGER)"
        );
        //Elimina los datos de los controles realizados
//        myDB.execSQL(
//            "DELETE from control"
//        );
        //Realizo consulta para saber si ya esta registrado
        val myCursor = myDB.rawQuery("select name from user", null)
        var name:String = ""
        while (myCursor.moveToNext()) {
            name = myCursor.getString(0)
            break
        }
        myCursor.close();
        //Capturamos el componente de Texto
        val editRegistrado=findViewById<EditText>(R.id.edtTextoRegistro)
        editRegistrado.setText(name)

        //Recupero el boton de registro y el texto escrito
        val btnRegistro = findViewById<Button>(R.id.btnRegistrar)
        val edtTexto = findViewById<EditText>(R.id.edtTextoRegistro)

        //Si ya esta registrado cambio el texto del boton a entrar
        if (name != "") {
            btnRegistro.setText("Entrar")
        }

        btnRegistro.setOnClickListener {
            //Creo el registro de datos
            val row1 = ContentValues()
            //asigno al nombre el valor entrado
            row1.put("name", edtTexto.text.toString());
            //Guardo el registro en la base local
            myDB.insert("user", null, row1);

            val intent = Intent(this, HomeActivity::class.java)
            //mecanismo para pasar directamente el dato del nombre registrado a la pantalla home
            intent.putExtra("nombreRegistro", edtTexto.text.toString())
            startActivity(intent)
        }
    }
}