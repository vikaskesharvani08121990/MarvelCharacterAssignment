package com.example.data.mapper

import com.example.data.entity.CharacterListResponse
import com.example.data.entity.MarvelCharacterResult
import com.example.data.entity.Thumbnail
import com.example.domain.model.MarvelCharacter

interface DataMapper {
    fun mapToListOfMarvelCharacter(root:CharacterListResponse): List<MarvelCharacter>
    fun mapToCharacterDetails(root:CharacterListResponse): MarvelCharacter?
    fun mapMarvelCharacter(result:MarvelCharacterResult): MarvelCharacter
    fun getSmallImage(thumbnail: Thumbnail?):String?
    fun getLargeImage(thumbnail: Thumbnail?):String
    fun getMediumImage(thumbnail: Thumbnail?):String
}