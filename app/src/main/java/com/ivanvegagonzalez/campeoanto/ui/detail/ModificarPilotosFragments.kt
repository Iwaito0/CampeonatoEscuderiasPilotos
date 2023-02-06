package com.ivanvegagonzalez.campeoanto.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.ivanvegagonzalez.campeoanto.R
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
        viewModel.piloto.observe(viewLifecycleOwner) { pilotos ->
            Log.d("PRUEBAaaa", pilotos.toString())
            /*val pru= R.id.nombreEscuderiaModificar.setT
            Log.d("EDIT", pru.toString())*/
        }
    }
}