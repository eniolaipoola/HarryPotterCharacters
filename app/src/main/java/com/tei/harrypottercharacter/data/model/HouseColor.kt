package com.tei.harrypottercharacter.data.model

import androidx.compose.ui.graphics.Color

enum class HouseColor(val color: Color) {
    GRIFFINDOR(Color(0xFF740001)),
    SLYTHERIN(Color(0xFF1A472A)),
    RAVENCLAW(Color(0xFF0C1A40)),
    HUFFLEPUFF(Color(0xFFEEB939));

    companion object {
        fun fromName(name: String?): HouseColor {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: GRIFFINDOR
        }
    }
}