package com.example.domain.model

data class CharacterDetails(
    var code: Int = 0,
    var status: String,
    var characterDetails: MarvelCharacter?
)