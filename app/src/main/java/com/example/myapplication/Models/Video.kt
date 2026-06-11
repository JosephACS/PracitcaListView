package com.example.myapplication.Models

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val titulo: String,
    val fecha_publicacion: String,
    val portadaVideo: String,
    val urlVideo: String
)
