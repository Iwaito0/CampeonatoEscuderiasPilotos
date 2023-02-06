package com.ivanvegagonzalez.campeoanto.ui.detail

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentDetailBinding
import com.ivanvegagonzalez.campeoanto.loadUrl
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import com.ivanvegagonzalez.campeoanto.model.Pilotos


class DetailFragmentPiloto : Fragment(R.layout.fragment_detail_piloto) {

    private  val viewModel: DetailViewModelPilotos by viewModels {
        DetailViewModelFactoryPilotos(arguments?.getParcelable<Pilotos>(EXTRA_PILOTO)!!)
    }
    companion object{
        const val EXTRA_PILOTO = "DetailActivity:Piloto"
    }

    //private val viewModel: DetailViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewModel.piloto.observe(viewLifecycleOwner){ pilotos ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = pilotos.nombre
            binding.imagen.loadUrl(pilotos.urlImagen)
            bindingDetail(binding.detalles, pilotos)
        }

        binding.btnBorrar.setOnClickListener {
            viewModel.borraPiloto()
            findNavController().navigate(
                //R.id.action_detailFragment_to_mainFragment
                R.id.action_detailFragmentPiloto_to_pilotoFragment
            )


        }

        binding.btnModificar.setOnClickListener{
            var nombre="";
            var primerApellido="";
            var segundoApellido="";
            var dni="";
            var urlImagen="";
            viewModel.piloto.observe(viewLifecycleOwner){ pilotos ->
                nombre=pilotos.nombre;
                primerApellido=pilotos.primerApellido;
                segundoApellido=pilotos.segundoApellido;
                dni=pilotos.dni
                urlImagen=pilotos.urlImagen
            }
            val pil = Pilotos(nombre,primerApellido,segundoApellido,dni,urlImagen)
            findNavController().navigate(
                R.id.action_detailFragmentPiloto_to_modificarPilotosFragments,
                bundleOf(ModificarPilotosFragments.PILOTOS_DATOS to pil)
            )

        }

        binding.fab.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Añadir a favoritos?")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        binding.fab.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_favorite_24))
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            builder.create()
            builder.show()
        }
    }

    private fun bindingDetail(detalles: TextView, piloto: Pilotos) {
        detalles.text = buildSpannedString {
            bold { append("Primer apellido: ") }
            appendLine(piloto.primerApellido)
            append()
            bold { append("Segundo apellido: ") }
            appendLine(piloto.segundoApellido)
            append()
            bold { append("DNI: ") }
            appendLine(piloto.dni.toString())
            append()
        }
    }


}

