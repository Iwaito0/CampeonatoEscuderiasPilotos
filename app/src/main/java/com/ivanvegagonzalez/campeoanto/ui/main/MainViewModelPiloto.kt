package com.ivanvegagonzalez.campeoanto.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.*

import com.ivanvegagonzalez.campeoanto.model.Pilotos
import com.ivanvegagonzalez.campeoanto.repository.DbFirestorePilotos
import kotlinx.coroutines.*

@SuppressLint("SuspiciousIndentation")
class MainViewModelPiloto(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)

                DbFirestorePilotos.getAllObservable().observeForever {
                    _state.value = _state.value?.copy(loading = false, pilotos = it)
                }
        }

/*        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false, pilotos = requestPilotos())
        }*/
    }

    private suspend fun requestPilotos(): List<Pilotos>  = DbFirestorePilotos.getAll()


    fun navigateTo(piloto: Pilotos) {
        _state.value = _state.value?.copy(navigateTo = piloto)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    fun navigateToCreate() {
        _state.value = _state.value?.copy(navigateToCreate = true)
    }

    fun navigateToCreateDone() {
        _state.value = _state.value?.copy(navigateToCreate = false)
    }

    data class UiState(
        val loading: Boolean = false,
        val pilotos: List<Pilotos>? = null,
        val navigateTo: Pilotos? = null,
        val navigateToCreate: Boolean = false
    )

}