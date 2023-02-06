package com.ivanvegagonzalez.campeoanto.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.model.Escuderias


class ModificarEscuderiaFragment: Fragment(R.layout.fragment_modificar_escuderia) {

    companion object{

        const val ESCUDERIA_DATOS= "DetailActivity:Escuderia"

    }

    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Escuderias>(ModificarEscuderiaFragment.ESCUDERIA_DATOS)!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Estp me funciona

        //val binding = ModificarEscuderiaFragment.bind(view)

        /*binding.buttonModificarEscuderias.setOnClickListener {
           binding*/

        viewModel.escuderia.observe(viewLifecycleOwner) { escuderias ->
            Log.d("PRUEBAaaa", escuderias.toString())
            /*val pru= R.id.nombreEscuderiaModificar.setT
            Log.d("EDIT", pru.toString())*/
        }
        }



    }

