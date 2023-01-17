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
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura.insert("ENTRENADOR", null, valoresAGuardar)
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenador(id: Int): Boolean{
        val conexionBaseDatos = writableDatabase
        val resultadoEliminar = conexionBaseDatos.delete(
            "ENTRENADOR",
        "id=?",// where clause
        arrayOf(id.toString()))
        conexionBaseDatos.close()
        return if(resultadoEliminar == -1) false else true
    }

    fun actualizarEntrenador(nombre: String, descripcion: String, idActualizar: Int): Boolean{
        val conexionBaseDatos = writableDatabase
        val valorActualizar = ContentValues()
        valorActualizar.put("nombre", nombre)
        valorActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionBaseDatos.update(
            "ENTRENADOR",
            valorActualizar,
            "id=?",
            arrayOf(idActualizar.toString())
        )
        conexionBaseDatos.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador{
        val baseDeDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scriptConsultarUsuario, //Consulta
            arrayOf(id.toString()) // Parametros de consulta
        )
        // Logica de busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        var usuarioEncontrado = BEntrenador(0, "", "")
        val arregloEntrenador = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)//Columna ID
                val nombre = resultadoConsultaLectura.getString(1)// Columna de nombre
                val descripcion = resultadoConsultaLectura.getString(3) // Columna de descripción
                if(id!= null){
                    usuarioEncontrado = BEntrenador(0, "", "")
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arregloEntrenador.add(usuarioEncontrado)
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return usuarioEncontrado
    }
}