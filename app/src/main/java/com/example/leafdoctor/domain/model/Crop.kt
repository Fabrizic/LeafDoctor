package com.example.leafdoctor.domain.model

data class Crop(
    val id: String,
    val nameResId: Int,
    val emoji: String,
    val isMore: Boolean = false
)

