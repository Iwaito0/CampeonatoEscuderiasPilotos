package com.ivanvegagonzalez.campeoanto.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ivanvegagonzalez.campeoanto.model.Escuderias
import com.ivanvegagonzalez.campeoanto.model.Pilotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


object DbFirestore {
    const val COLLECTION_ESCUDERIA = "escuderias"

    //Datos escuderias
    suspend fun getAll(): List<Escuderias> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
            .get()
            .await()
        val escuderias = mutableListOf<Escuderias>()
        for (documentSnapshot in snapshot){
            val escuderia = documentSnapshot.toObject(Escuderias::class.java)
            escuderias.add(escuderia)
        }
        return escuderias
    }

    suspend fun createEscuderia(escuderia: Escuderias){
        FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
            //.document("prueba").set(escuderia)
            .add(escuderia)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_ESCUDERIA, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_ESCUDERIA, it.toString())
            }

    }

    fun borraEscuderia(escuderia: Escuderias) {
        FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
            .whereEqualTo("nombre", escuderia.nombre)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_ESCUDERIA, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_ESCUDERIA, it.toString())
                        }
                }
            }
    }

    fun modificaEscuderiaTitulo(escuderia: Escuderias?, title: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
            .whereEqualTo("titulo", title)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
                        .document(id)
                        .update("titulo", escuderia?.nombre)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_ESCUDERIA, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_ESCUDERIA, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Escuderias>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Escuderias>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_ESCUDERIA)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Escuderias>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Escuderias::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

}