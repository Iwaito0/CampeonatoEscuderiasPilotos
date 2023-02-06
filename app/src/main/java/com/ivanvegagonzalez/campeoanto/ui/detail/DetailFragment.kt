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


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Escuderias>(EXTRA_ESCUDERIA)!!)
    }
    companion object{
        const val EXTRA_ESCUDERIA = "DetailActivity:Escuderia"
    }

    //private val viewModel: DetailViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewModel.escuderia.observe(viewLifecycleOwner){ escuderias ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = escuderias.nombre
            binding.imagen.loadUrl(escuderias.urlImagen)
            bindingDetail(binding.detalles, escuderias)
        }

        binding.btnBorrar.setOnClickListener {
            viewModel.borraEscuderia()
            findNavController().navigate(
                R.id.action_detailFragment_to_mainFragment
            )


        }

        binding.btnModificar.setOnClickListener{
            //Por aqui
            var nombre="";
            var paisProcedencia="";
            var carrerasGanadas=0;
            var urlImagen="";
            viewModel.escuderia.observe(viewLifecycleOwner){ escuderias ->
                nombre=escuderias.nombre;
                paisProcedencia=escuderias.paisProcedencia;
                carrerasGanadas=escuderias.carrerasGanadas;
                urlImagen=escuderias.urlImagen
            }
            val escu=Escuderias(nombre,paisProcedencia,carrerasGanadas,urlImagen);
            findNavController().navigate(
                R.id.action_detailFragment_to_modificarEscuderiaFragment,
                    bundleOf(ModificarEscuderiaFragment.ESCUDERIA_DATOS to escu)
            )
            //viewModel.onNavigateDone()

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

    private fun bindingDetail(detalles: TextView, escuderia: Escuderias) {
        detalles.text = buildSpannedString {
            bold { append("Pais de procedencia: ") }
            appendLine(escuderia.paisProcedencia)
            append()
            bold { append("Carreras ganadas: ") }
            appendLine(escuderia.carrerasGanadas.toString())
            append()
        }
    }


}

