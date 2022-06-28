package com.example.data.mapper

import com.example.data.entity.CharacterListResponse
import com.example.data.entity.MarvelCharacterResult
import com.example.data.entity.Thumbnail
import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterList
import com.example.domain.model.MarvelCharacter

interface DataMapper {
    fun mapToRootObject(root:CharacterListResponse): CharacterList
    fun mapToRootDetails(root:CharacterListResponse): CharacterDetails
    fun mapMarvelCharacter(result:MarvelCharacterResult): MarvelCharacter
    fun getSmallImage(thumbnail: Thumbnail?):String?
    fun getLargeImage(thumbnail: Thumbnail?):String
    fun getMediumImage(thumbnail: Thumbnail?):String
}