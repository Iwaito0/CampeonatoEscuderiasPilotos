package com.ivanvegagonzalez.campeoanto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Escuderias(
    val nombre: String = "",
    val paisProcedencia: String = "",
    val carrerasGanadas: Int = 0,
    val urlImagen: String = "",
    ): Parcelable {
}