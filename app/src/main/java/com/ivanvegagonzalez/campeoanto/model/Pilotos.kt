package com.ivanvegagonzalez.campeoanto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pilotos(
    val nombre: String = "",
    val primerApellido: String = "",
    val segundoApellido: String = "",
    val dni: String = "",
    val urlImagen: String = "",
    ): Parcelable {
}