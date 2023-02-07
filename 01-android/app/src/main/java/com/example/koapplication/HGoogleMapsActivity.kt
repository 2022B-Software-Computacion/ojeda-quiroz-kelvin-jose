package com.example.koapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*

class HGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps2)
        solicitarPermisos()
        iniciarLogicaMapa()
    }



    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            if (googleMap != null) {
                mapa = googleMap
                val zoom = 17f
                establecerConfiguracionMapa()
                val quicentro = LatLng(
                    -0.17578166060572215, -78.48025629931664
                )
                val titulo = "Quicentro"
                val markQuicentro = anadirMarcador(quicentro, titulo)
                markQuicentro.tag = titulo

                val poliLineaUno = googleMap.addPolyline(
                    PolylineOptions().clickable(true).add(
                        LatLng(-0.18126856835759267, -78.48374387877126),
                        LatLng(-0.17736329123423922, -78.48281047008264),
                        LatLng(-0.17980945392555506, -78.48171612886149)
                    )
                )
                poliLineaUno.tag = "Linea-1" //<- Id de linea

                val poligonoUno = googleMap.addPolygon(
                    PolygonOptions().clickable(true).add(
                        LatLng(-0.17339900041575654, -78.48277635456176),
                        LatLng(-0.17540828953277474, -78.48322286530848),
                        LatLng(-0.1723615242520871, -78.47934872794737)
                    )
                )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2"
                escucharListeners()
            }
        }
    }


    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation =
            ContextCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION)
        val tienesPermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienesPermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this //Contexto
                , arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), // Arreglo de permisos
                1//Código de peticion de los permisos
            )
        }

    }

    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(MarkerOptions().position(latLng).title(title))!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    private fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        // with verifica que la variable no sea nula
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienesPermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienesPermisos) {
                mapa.isMyLocationEnabled = true //Tenemos permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true // no tenemos aun permisos
        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag //ID
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
            it.tag //ID
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag //ID
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }
}