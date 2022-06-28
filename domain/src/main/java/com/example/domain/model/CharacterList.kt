package com.example.domain.model


data class CharacterList(
    var code: Int = 0,
    var status: String,
    var charactersList: List<MarvelCharacter>
)


