package com.example.rustoremvp.model

import android.accessibilityservice.GestureDescription
import android.media.MediaRouter
import android.media.Rating

data class AppModel(
    val id: Int,
    val iconRes: Int,
    val name: String,
    val shortDescription: String,
    val company: String,
    val category: String,
    val rating: String,
    val screenshots: List<Int> = emptyList(),
    val fullDescription: String = "",
)