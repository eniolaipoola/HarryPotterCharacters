package com.tei.harrypottercharacter.util

import java.text.SimpleDateFormat
import java.util.Locale

fun getCharacterStatus(alive: Boolean?): String? {
    return when (alive) {
        true -> {
            ALIVE
        }
        false -> {
            DEAD
        }
        else -> {
            ""
        }
    }
}

fun formatDateOfBirth(dateString: String?): String? {
    if (dateString.isNullOrBlank()) return ""
    return try {
        val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception){
        ""
    }
}