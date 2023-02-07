package com.ivanvegagonzalez.campeoanto.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentModificarPilotosFragmentsBinding
import com.ivanvegagonzalez.campeoanto.model.Pilotos

class ModificarPilotosFragments: Fragment(R.layout.fragment_modificar_pilotos_fragments) {

    companion object{

        const val PILOTOS_DATOS= "DetailActivity:Piloto"

    }

    private  val viewModel: DetailViewModelPilotos by viewModels {
        DetailViewModelFactoryPilotos(arguments?.getParcelable<Pilotos>(ModificarPilotosFragments.PILOTOS_DATOS)!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nombrePiloto="";

        val binding = FragmentModificarPilotosFragmentsBinding.bind(view)

        viewModel.piloto.observe(viewLifecycleOwner) { pilotos ->
            nombrePiloto=pilotos.nombre.toString();
            binding.nuevoNombrePiloto.setText(pilotos.nombre.toString())
            binding.nuevoPrimerApellidoPiloto.setText(pilotos.primerApellido.toString())
            binding.nuevoSegundoApellidoPiloto.setText(pilotos.segundoApellido.toString())
            binding.nuevoDNIPiloto.setText(pilotos.dni.toString())
            binding.nuevaFotografiaPiloto.setText(pilotos.urlImagen.toString())

        }
        binding.buttonModificarPiloto.setOnClickListener {

            val nombre = binding.nuevoNombrePiloto.text.toString()
            val primerApellido = binding.nuevoPrimerApellidoPiloto.text.toString()
            val segundoApellido=binding.nuevoSegundoApellidoPiloto.text.toString()
            val dni = binding.nuevoDNIPiloto.text.toString()
            val url = binding.nuevaFotografiaPiloto.text.toString()

            val piloto= Pilotos(nombre.toString(),primerApellido.toString(),
                segundoApellido.toString(),dni.toString(),url.toString())

            viewModel.modificaPiloto(piloto,nombrePiloto)
            findNavController().navigate(
                R.id.action_modificarPilotosFragments_to_pilotoFragment
            )

        }

    }
}