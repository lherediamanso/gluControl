package com.example.glucontrol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random
import kotlin.random.nextInt


class HomeActivity : AppCompatActivity() {

    //Listado Imagenes RecyclerView
    private val imagenes= listOf (
        Imagenes("Corazón",R.drawable.img_5),
        Imagenes("Azúcar",R.drawable.img_7),
        Imagenes("Presión",R.drawable.img_2),
        Imagenes("Peso",R.drawable.img_3),
        Imagenes("Cardio",R.drawable.img_4)
    )

    //RecyclerView de imagenes
    private lateinit var rvImagenes:RecyclerView
    private lateinit var imagenesAdapter: ImagenesAdapter
    //RecyclerView de controles de glucosa
    private lateinit var rvControlGlucosa:RecyclerView
    private lateinit var glucosaAdapter: GlucosaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Capturamos el componente de Texto
        val txtNombreRegistrado=findViewById<TextView>(R.id.textHomeNombreRegistrado)
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

        //ReciclerView de imagenes
        rvImagenes = findViewById(R.id.rvImagenes)
        imagenesAdapter = ImagenesAdapter(imagenes)
        rvImagenes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvImagenes.adapter=imagenesAdapter

        //Cambiando texto del consejo del dia
        val txtConsejo=findViewById<TextView>(R.id.textConsejo)
        txtConsejo.text = "Consejo: " + concejoAleatorioDelDia()

        //agrego accion a boton de registrar control glucosa
        val button:FloatingActionButton = findViewById<FloatingActionButton>(R.id.btnAdicionaControl)
        button.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    ControlActivity::class.java
                )
            )
        }

        //Realizo consulta
        val myCursorControl = myDB.rawQuery("select fecha,valor from control ORDER BY fecha DESC", null)
        val valores = ArrayList<Glucosa>()
        while (myCursorControl.moveToNext()) {
            var valorGlucosaTem = Glucosa(myCursorControl.getString(0), myCursorControl.getString(1))
            valores.add(valorGlucosaTem)
        }
        myCursorControl.close();

        //ReciclerView de imagenes
        rvControlGlucosa = findViewById(R.id.rvControles)
        glucosaAdapter = GlucosaAdapter(valores)
        rvControlGlucosa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvControlGlucosa.adapter=glucosaAdapter
    }

    fun concejoAleatorioDelDia():String {
        val list = ConsejosDieta.values()
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        return randomElement.consejo
    }

}