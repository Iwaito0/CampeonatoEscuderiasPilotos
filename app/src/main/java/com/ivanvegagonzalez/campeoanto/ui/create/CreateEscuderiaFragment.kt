package com.ivanvegagonzalez.campeoanto.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentCreateEscuderiaBinding
import com.ivanvegagonzalez.campeoanto.model.Escuderias


class CreateEscuderiaFragment : Fragment(R.layout.fragment_create_escuderia) {
    private val viewModel: CreateEscuderiaViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateEscuderiaBinding.bind(view)

        binding.buttonCrearEscuderias.setOnClickListener {
            val nombre = Escuderias(binding.nombreEscuderia.text.toString())
            /*val textoCarrera = Escuderias(binding.carreraEscuderia.text.toString())
            val carrera=Integer.parseInt(textoCarrera.toString());
            val pais = Escuderias(binding.paisEscuderia.text.toString())
            val url = "ninguna"

            val escuderia= Escuderias(nombre.toString(),pais.toString(),carrera,url)
            */
            viewModel.createEscuderia(nombre)
            findNavController().navigate(
                R.id.action_createEscuderiaFragment_to_mainFragment)
        }
    }
}