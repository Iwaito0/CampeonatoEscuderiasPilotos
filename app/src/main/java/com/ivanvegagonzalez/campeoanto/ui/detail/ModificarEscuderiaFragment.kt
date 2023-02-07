package com.ivanvegagonzalez.campeoanto.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentModificarEscuderiaBinding
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
        var nombreEscuderia="";

        val binding = FragmentModificarEscuderiaBinding.bind(view)

        viewModel.escuderia.observe(viewLifecycleOwner) { escuderias ->
            nombreEscuderia=escuderias.nombre.toString();
            binding.nombreEscuderiaModificar.setText(escuderias.nombre.toString())
            binding.paisEscuderiaModificar.setText(escuderias.paisProcedencia.toString())
            binding.carreraEscuderiaModificar.setText(escuderias.carrerasGanadas.toString())
            binding.enlaceNuevaFotografia.setText(escuderias.urlImagen.toString())
        }

        binding.buttonModificarEscuderias.setOnClickListener {

            val nombre = binding.nombreEscuderiaModificar.text.toString()
            val textoCarrera = binding.carreraEscuderiaModificar.text.toString()
            val carrera=Integer.parseInt(textoCarrera.toString());
            val pais = binding.paisEscuderiaModificar.text.toString()
            val url = binding.enlaceNuevaFotografia.text.toString()

            val escuderia= Escuderias(nombre.toString(),pais.toString(),carrera,url.toString())

            viewModel.modificaEscuderia(escuderia,nombreEscuderia)
            findNavController().navigate(
                R.id.action_modificarEscuderiaFragment_to_mainFragment
            )

        }



    }
}

