package com.ivanvegagonzalez.campeoanto.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.ViewPilotoBinding
import com.ivanvegagonzalez.campeoanto.inflate
import com.ivanvegagonzalez.campeoanto.loadUrl
import com.ivanvegagonzalez.campeoanto.model.Pilotos


class PilotosAdapter(val listener: (Pilotos) -> Unit):
    RecyclerView.Adapter<PilotosAdapter.ViewHolder>() {

    var pilotos = emptyList<Pilotos>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_piloto, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val piloto = pilotos[position]
        holder.bind(piloto)

        holder.itemView.setOnClickListener {
            listener(piloto)
        }
    }

    override fun getItemCount(): Int = pilotos.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewPilotoBinding.bind(view)
         fun bind(piloto: Pilotos){
             binding.title.text = piloto.nombre

             piloto.urlImagen?.let { binding.imagen.loadUrl(it) }
         }
    }
}