package com.example.glucontrol

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.slider.RangeSlider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ControlActivity : AppCompatActivity() {

    //Variable
    private var glucosa:Int = 90
    //Componentes
    private lateinit var txtGlucosa: TextView
    private lateinit var rngGlucosa: RangeSlider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        //Capturamos el componente de Texto
        val txtNombreRegistrado=findViewById<TextView>(R.id.textHomeNombreRegistradoReg)
        //busco en la base de datos el usuario registrado
        val myDB = openOrCreateDatabase("glucontrol.db", MODE_PRIVATE, null)
        //Realizo consulta
        val myCursor = myDB.rawQuery("select name from user", null)
        var name:String = ""
        while (myCursor.moveToNext()) {
            name = myCursor.getString(0)
            break
        }
        myCursor.close();
        //mostramos la informacion en el texto
        txtNombreRegistrado.text = name

        //Inicializo componente
        txtGlucosa = findViewById(R.id.txtGlucosa)
        txtGlucosa.text = "${glucosa.toString()} mg/dl"
        rngGlucosa = findViewById(R.id.rngGlucosa)
        //Listener de glucosa
        rngGlucosa.addOnChangeListener { slider, value, fromUser ->
            glucosa = value.toInt()
            txtGlucosa.text = "${glucosa.toString()} mg/dl"
        }

        val btnRegistro = findViewById<Button>(R.id.btnGuardarControl)
        btnRegistro.setOnClickListener {
            //Creo el registro de datos
            val row1 = ContentValues()
            //asigno al nombre el valor entrado
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)
            row1.put("fecha", current);
            row1.put("valor", glucosa);
            //Guardo el registro en la base local
            myDB.insert("control", null, row1);

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}