package com.example.koapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionButton: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById<Button>(R.id.btn_dar_like)
            accionButton.setOnClickListener { anadirLike() }

        }

        fun anadirLike() {
            numeroLikes += 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
//            recyclerView.adapter!!.notifyDataSetChanged()
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_vista, parent, false)
        return  MyViewHolder(itemView)
    }
    // Setear los datos para la iteracion
    override fun onBindViewHolder(
        holder: FRecyclerViewAdaptadorNombreCedula.MyViewHolder,
        position: Int
    ) {
       val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.accionButton.text = "Like ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }
    // Tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}