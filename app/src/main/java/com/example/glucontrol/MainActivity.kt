package com.example.glucontrol

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creo base datos SQLite para guardar registros localmente
        val myDB = openOrCreateDatabase("glucontrol.db", MODE_PRIVATE, null)
        //creo tablas necesarias
        myDB.execSQL(
            "CREATE TABLE IF NOT EXISTS user (name VARCHAR(200))"
        );
        //Recupero el boton de registro y el texto escrito
        val btnRegistro = findViewById<Button>(R.id.btnRegistrar)
        val edtTexto = findViewById<EditText>(R.id.edtTextoRegistro)

        btnRegistro.setOnClickListener {
            //Creo el registro de datos
            val row1 = ContentValues()
            //asigno al nombre el valor entrado
            row1.put("name", edtTexto.text.toString());
            //Guardo el registro en la base local
            myDB.insert("user", null, row1);

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("nombreRegistro", edtTexto.text.toString())
            startActivity(intent)
        }
    }
}