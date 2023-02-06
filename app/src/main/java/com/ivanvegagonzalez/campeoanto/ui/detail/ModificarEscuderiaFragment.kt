package com.ivanvegagonzalez.campeoanto.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentDetailBinding
import com.ivanvegagonzalez.campeoanto.loadUrl
import com.ivanvegagonzalez.campeoanto.model.Escuderias


class ModificarEscuderiaFragment: Fragment(R.layout.fragment_modificar_escuderia) {

    companion object{
        const val ESCUDERIA_DATOS= "DetailActivity:Escuderia"

    }


   /*
    companion object{
        const val EXTRA_ESC= "DetailActivity:Escuderia"
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("PRUEBA", ESCUDERIA_DATOS)
       //Log.d("PRUEBA", prueba.toString())
       // val binding = FragmentDetailBinding.bind(view)
        /* Log.d("PRUEBA", EXTRA_ESC)
         Log.d("PRUEBA", view.toString())
         Log.d("PRUEBA", EXTRA_ESC)*/
    }



/* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        // val binding = FragmentDetailBinding.bind(view)
                /* Log.d("PRUEBA", EXTRA_ESC)
                 Log.d("PRUEBA", view.toString())
                 Log.d("PRUEBA", EXTRA_ESC)*/
         }*/

   /* private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Escuderias>(ModificarEscuderiaFragment.EXTRA_ESC)!!)
    }
    companion object{
        const val EXTRA_ESC = "ModificarEscuderia:Escuderia"
    }*/


  /* @SuppressLint("UseCompatLoadingForDrawables")
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       val binding = FragmentDetailBinding.bind(view)

       (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

       viewModel.escuderia.observe(viewLifecycleOwner) { escuderias ->
        Log.d("Pruebas", escuderias.toString())
       /*(requireActivity() as AppCompatActivity).supportActionBar?.title = escuderias.nombre
           binding.imagen.loadUrl(escuderias.urlImagen)
           binding.detalles*/
       }

   }*/


    /* fun modificarEscuderia() {
         //setContentView(R.layout.fragment_modificar_escuderia)

         //val nombre = escuderia.value?.nombre
         //escuderia.value = _escuderia.value?.copy(nombre = "$nombre modificado")

         //Log.d("NOMBRE", nombre!!)
         //Log.d("VALOReSCUDERIA", _escuderia.value.toString())
        // Log.d("VALOReSCUDERIA","AAAAAAA")
     }*/
}