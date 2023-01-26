package com.example.koapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador.add(BEntrenador(1, "Kelvin", "k@gmail.com"))
        listaEntrenador.add(BEntrenador(2, "Ojeda", "o@gmail.com"))
        val recyclerView = findViewById<RecyclerView>(R.id.rv_entrenadores)
        incializarRecyclerView(listaEntrenador, recyclerView)
    }

    fun incializarRecyclerView(listaEntrenador: ArrayList<BEntrenador>, recyclerView: RecyclerView) {
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            this,
            listaEntrenador,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes+=1
        val totalLikesTextView = findViewById<TextView>(R.id.tv_total_likes)
        totalLikesTextView.text =totalLikes.toString()
    }
}