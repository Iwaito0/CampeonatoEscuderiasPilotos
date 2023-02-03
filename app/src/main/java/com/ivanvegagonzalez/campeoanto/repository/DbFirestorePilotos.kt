package com.ivanvegagonzalez.campeoanto.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ivanvegagonzalez.campeoanto.model.Pilotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


object DbFirestorePilotos {
    const val COLLECTION_PILOTO= "pilotos"

    suspend fun getAll(): List<Pilotos> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
            .get()
            .await()
        val pilotos = mutableListOf<Pilotos>()
        for (documentSnapshot in snapshot){
            val piloto = documentSnapshot.toObject(Pilotos::class.java)
            pilotos.add(piloto)
        }
        return pilotos
    }

    suspend fun createPiloto(piloto: Pilotos){
        FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
            .add(piloto)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_PILOTO, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_PILOTO, it.toString())
            }

    }

    fun borraPiloto(piloto: Pilotos) {
        FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
            .whereEqualTo("nombre", piloto.nombre)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_PILOTO, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_PILOTO, it.toString())
                        }
                }
            }
    }

    fun modificaPilotoTitulo(piloto: Pilotos?, title: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
            .whereEqualTo("titulo", title)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
                        .document(id)
                        .update("titulo", piloto?.nombre)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_PILOTO, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_PILOTO, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Pilotos>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Pilotos>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_PILOTO)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Pilotos>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Pilotos::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

}