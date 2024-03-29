package com.example.koapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.util.*
import kotlin.collections.ArrayList

class JFirebaseFirestone : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<JCitiesDto> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jfirebase_firestone)
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,// como se va a ver
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener {
            crearDatosDePrueba()
        }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrderBy(adaptador) }

        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener { consultarDocumento(adaptador) }
        val botonFirebaseIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonFirebaseIndiceCompuesto.setOnClickListener { consultarindiceCompuesto(adaptador) }

        val botonFirebaseCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonFirebaseCrear.setOnClickListener { crearDatosEjemplo() }

        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar.setOnClickListener { eliminarRegistro() }

        val botonFirebaseEmpiezaPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonFirebaseEmpiezaPaginar.setOnClickListener {
            query = null
            consultarCiudad(adaptador)
        }
    }

    //paginación
    //[1,2,3,4,5,6,7,8]
    // primero pido los 4 [1,2,3,4]
    // siguientes 4 -> tengo que pasar el 4 = [5,6,7,8]
    // siguientes 4 -> tengo que pasar el 8 = []

    private fun consultarCiudad(adaptador: ArrayAdapter<JCitiesDto>) {
        val db = Firebase.firestore
        val citiesRef = db.collection("cities").orderBy("population").limit(1)
        var tarea: Task<QuerySnapshot>? = null
        if(query == null){
            tarea = citiesRef.get()  //1era vez
            adaptador.notifyDataSetChanged()
        }else{
            tarea = query!!.get() //Consultar la consulta anterior pero con más datos en el nuevo documento
        }
    }




    private fun eliminarRegistro() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
            .document("123456789")
            .collection("estudiante")
        referenciaEjemploEstudiante
            .document("123456789")
            .delete()
            .addOnCompleteListener {

            }
            .addOnFailureListener {

            }
    }

    private fun crearDatosEjemplo() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
            .document("123456789")
            .collection("estudiante")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Adrian",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "Mitad del mundo",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )

        // COn identificador quemado
        referenciaEjemploEstudiante
            .document("123456789")
            .set(datosEstudiante)
            .addOnCompleteListener {

            }
            .addOnFailureListener {

            }
        // Con identificador generado en Date.time
        referenciaEjemploEstudiante
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnCompleteListener {

            }
            .addOnFailureListener {

            }

        // Sin identificador generado por el firebase
        referenciaEjemploEstudiante
            .add(datosEstudiante)// Crear
            .addOnCompleteListener {

            }
            .addOnFailureListener {

            }
    }

    private fun consultarindiceCompuesto(adaptador: ArrayAdapter<JCitiesDto>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        citiesRefUnico.whereEqualTo("capital", false).whereLessThanOrEqualTo("population", 40000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloCiudad(arreglo, ciudad, adaptador)
                }
            }

    }

    private fun consultarDocumento(adaptador: ArrayAdapter<JCitiesDto>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        citiesRefUnico.document("BJ")
            .get()
            .addOnSuccessListener {
                limpiarArreglo()
                arreglo.add(
                    JCitiesDto(
                        it.data?.get("name") as String?,
                        it.data?.get("state") as String?,
                        it.data?.get("name") as String?,
                        it.data?.get("country") as Boolean?,
                        it.data?.get("population") as Long?,
                        it.data?.get("regions") as ArrayList<String>
                    )
                )
            }
        adaptador.notifyDataSetChanged()

    }

    fun consultarConOrderBy(adaptador: ArrayAdapter<JCitiesDto>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico.orderBy("population", Query.Direction.ASCENDING)
            .get().addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloCiudad(arreglo, ciudad, adaptador)
                }
            }
    }

    private fun crearDatosDePrueba() {

    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore // Objeto Firebase
        val cities = db.collection("cities") // nombre de la colección

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)   // Asigna ID que es "SF"

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun anadirAArregloCiudad(
        arregloNuevo: ArrayList<JCitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<JCitiesDto>
    ) {
        val nuevaCiudad = JCitiesDto(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>
        )

        arregloNuevo.add(nuevaCiudad)
        adaptador.notifyDataSetChanged()

    }


}