package com.ivanvegagonzalez.campeoanto.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ivanvegagonzalez.campeoanto.repository.DbFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias

class DetailViewModel(escuderia: Escuderias): ViewModel() {

    fun borraEscuderia() {
        DbFirestore.borraEscuderia(escuderia.value!!)
    }

    fun modificaEscuderia(escuderia: Escuderias, nombreEscuderia: String) {
        DbFirestore.modificaEscuderia(escuderia, nombreEscuderia)
    }

    private val _escuderia = MutableLiveData(escuderia)
    val escuderia: LiveData<Escuderias> get() = _escuderia
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val escuderia: Escuderias): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(escuderia) as T
    }

}