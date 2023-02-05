package com.ivanvegagonzalez.campeoanto.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentCreateEscuderiaBinding
import com.ivanvegagonzalez.campeoanto.databinding.FragmentCreatePilotoBinding
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import com.ivanvegagonzalez.campeoanto.model.Pilotos


class CreatePilotoFragment : Fragment(R.layout.fragment_create_piloto) {
    private val viewModel: CreatePilotoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreatePilotoBinding.bind(view)

        binding.buttonCrearPiloto.setOnClickListener {
            //añadir piloto
            val nombre = binding.nombrePiloto.text.toString()
            val apellido1 = binding.primerApellidoPiloto.text.toString()
            val apellido2=binding.segundoApellidoPiloto.text.toString()
            val dni = binding.DNIPiloto.text.toString()
            val url = "ninguna"

            val piloto= Pilotos(
                nombre.toString(),
                apellido1.toString(),
                apellido2.toString(),
                dni.toString(),
                url
            )

            viewModel.createPiloto(piloto)
            findNavController().navigate(
                //R.id.action_createEscuderiaFragment_to_mainFragment
                R.id.action_createPilotoFragment_to_pilotoFragment
            )
        }
    }
}