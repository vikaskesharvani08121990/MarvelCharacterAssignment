package com.example.domain.testUtils

import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.model.MarvelCharacter

object GetDomainFakeData {
    fun getMarvelCharacterList(): DomainMatcherCharacterListResponse {
        var list=ArrayList<MarvelCharacter>()
       var dataObj= MarvelCharacter(name = "A-Bomb (HAS)", id = 1017100 , image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_small.jpg",
            bigImage ="http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg" , description =
            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ")
        list.add(dataObj)
        list.add(dataObj)
        return DomainMatcherCharacterListResponse(code=200, status = "Ok", charactersList = emptyList())
    }

    fun getMarvelCharacterDetails(): DomainMatcherCharacterListResponse {
        var list=ArrayList<MarvelCharacter>()
        list.add(MarvelCharacter(name = "A-Bomb (HAS)", id = 1017100 , image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_small.jpg",
            bigImage ="http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg" , description =
            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! "))
        return DomainMatcherCharacterListResponse(code=200, status = "Ok", charactersList = emptyList())
    }
}

