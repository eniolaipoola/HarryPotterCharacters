package com.tei.harrypottercharacter.util

import java.text.SimpleDateFormat
import java.util.Locale

fun getCharacterStatus(alive: Boolean?): String? {
    return if (alive != null && alive == true) {
        ALIVE
    } else {
        DEAD
    }
}

fun formatDateOfBirth(dateString: String): String? {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(dateString)
    return if (date != null) {
        outputFormat.format(date)
    } else {
        ""
    }
}