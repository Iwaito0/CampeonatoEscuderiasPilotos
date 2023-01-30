package com.ivanvegagonzalez.campeoanto.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.model.Escuderias


class ModificarEscuderiaFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Choose authentication providers
    }


    fun modificarEscuderia(escuderia: Escuderias?, title: String){
        setContentView(R.layout.fragment_modificar_escuderia)

    }
}