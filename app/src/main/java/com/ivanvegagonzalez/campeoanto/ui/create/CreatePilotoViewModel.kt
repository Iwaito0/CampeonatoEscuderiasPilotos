package com.ivanvegagonzalez.campeoanto.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanvegagonzalez.campeoanto.repository.DbFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import com.ivanvegagonzalez.campeoanto.model.Pilotos
import com.ivanvegagonzalez.campeoanto.repository.DbFirestorePilotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreatePilotoViewModel: ViewModel() {

    fun createPiloto(pilotos: Pilotos){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestorePilotos.createPiloto(pilotos)
        }

    }
}