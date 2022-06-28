package com.example.domain.testUtils

import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterList
import com.example.domain.model.MarvelCharacter

object GetDomainFakeData {
    fun getMarvelCharacterList(): CharacterList {
        var list=ArrayList<MarvelCharacter>()
       var dataObj= MarvelCharacter(name = "A-Bomb (HAS)", id = 1017100 , image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_small.jpg",
            bigImage ="http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg" , description =
            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ")
        list.add(dataObj)
        list.add(dataObj)
        return CharacterList(code=200, status = "Ok", charactersList = list)
    }

    fun getMarvelCharacterDetails(): CharacterDetails {
        var data=MarvelCharacter(name = "A-Bomb (HAS)", id = 1017100 , image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_small.jpg",
            bigImage ="http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg" , description =
            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ")
        return CharacterDetails(code=200, status = "Ok", characterDetails = data)
    }
}

