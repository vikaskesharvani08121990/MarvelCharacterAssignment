package com.example.domain

import com.example.domain.model.MarvelCharacter

val marvelCharacterDetails = MarvelCharacter(
    name = "A-Bomb (HAS)",
    id = 1017100,
    image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_small.jpg",
    bigImage = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg",
    description =
    "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! "
)
val marvelCharacterList = listOf(marvelCharacterDetails, marvelCharacterDetails)

const val publicKey = "9eda5d952e9fb001de15ad03bcfdd1a8"
const val privateKey = "76162ae556a83dd9872d502bec095330ddec9ca4"
const val characterId = 1017100