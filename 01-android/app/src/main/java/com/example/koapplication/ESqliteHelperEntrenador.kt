package com.example.koapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(contexto: Context?) : SQLiteOpenHelper(contexto, "moviles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTableEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTableEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearEntrenador(nombre: String, descripcion: String): Boolean{
        val baseDatosEscribura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", "Kelvin")
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscribura.insert("ENTRENADOR", null, valoresAGuardar)
        baseDatosEscribura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }
}