package com.ivanvegagonzalez.campeoanto.ui.main

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.*
import com.ivanvegagonzalez.campeoanto.model.DbFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import kotlinx.coroutines.*

@SuppressLint("SuspiciousIndentation")
class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)

                DbFirestore.getAllObservable().observeForever {
                    _state.value = _state.value?.copy(loading = false, escuderias = it)
                }
        }

/*        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false, escuderias = requestEscuderias())
        }*/
    }

    private suspend fun requestEscuderias(): List<Escuderias>  = DbFirestore.getAll()


    fun navigateTo(escuderia: Escuderias) {
        _state.value = _state.value?.copy(navigateTo = escuderia)
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
        val escuderias: List<Escuderias>? = null,
        val navigateTo: Escuderias? = null,
        val navigateToCreate: Boolean = false
    )

}