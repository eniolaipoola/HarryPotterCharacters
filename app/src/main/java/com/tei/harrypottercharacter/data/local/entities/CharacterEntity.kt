package com.tei.harrypottercharacter.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tei.harrypottercharacter.data.model.Wand

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val yearOfBirth: Int,
    val wizard: Boolean,
    val ancestry: String,
    val wand: Wand,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alternateActors: List<String>,
    val alive: Boolean,
    val image: String
)