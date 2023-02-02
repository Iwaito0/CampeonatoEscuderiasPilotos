package com.ivanvegagonzalez.campeoanto.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanvegagonzalez.campeoanto.repository.DbFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEscuderiaViewModel: ViewModel() {

    fun createEscuderia(escuderias: Escuderias){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createEscuderia(escuderias)
        }

    }
}