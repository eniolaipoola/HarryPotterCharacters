package com.tei.harrypottercharacter.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.model.Wand

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val alternateNames: List<String>?,
    val species: String?,
    val gender: String?,
    val house: String?,
    val dateOfBirth: String?,
    val yearOfBirth: Int?,
    val wizard: Boolean?,
    val ancestry: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val wand: Wand?,
    val patronus: String?,
    val hogwartsStudent: Boolean?,
    val hogwartsStaff: Boolean?,
    val actor: String?,
    val alternateActors: List<String>?,
    val alive: Boolean?,
    val image: String?
)

fun CharacterEntity.toDomainModel() = CharacterModel(
    id,
    name,
    alternateNames,
    species,
    gender,
    house,
    dateOfBirth,
    yearOfBirth,
    wizard,
    ancestry,
    eyeColor,
    hairColor,
    wand,
    patronus,
    hogwartsStudent,
    hogwartsStaff,
    actor,
    alternateActors,
    alive,
    image
)