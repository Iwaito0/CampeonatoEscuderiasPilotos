package com.ivanvegagonzalez.campeoanto.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ivanvegagonzalez.campeoanto.repository.DbFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import com.ivanvegagonzalez.campeoanto.model.Pilotos
import com.ivanvegagonzalez.campeoanto.repository.DbFirestorePilotos

class DetailViewModelPilotos(piloto: Pilotos): ViewModel() {

    fun borraPiloto() {
        DbFirestorePilotos.borraPiloto(piloto.value!!)
    }

    fun modificaPiloto() {
       /* val nombre = escuderia.value?.nombre
        Log.d("PRUEBANOMBRE",nombre.toString())
        Log.d("PRUEBAArrayEscuderia",escuderia.value.toString())*/

        //Por aqui
        //ModificarEscuderiaFragment.modificarEscuderia(_escuderia.value, nombre!!)

        //DbFirestore.modificaEscuderiaTitulo(_escuderia.value, nombre!!)
    }

    private val _piloto = MutableLiveData(piloto)
    val piloto: LiveData<Pilotos> get() = _piloto
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactoryPilotos(private val piloto: Pilotos): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModelPilotos(piloto) as T
    }

}