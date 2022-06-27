package com.example.data.mapper

import com.example.data.entity.DataCharacterListDTO
import com.example.data.entity.MarvelCharacterResult
import com.example.data.entity.Thumbnail
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.model.MarvelCharacter

interface DataMapper {
    fun mapToRootObject(root:DataCharacterListDTO): DomainMatcherCharacterListResponse
    fun mapMarvelCharacter(result:MarvelCharacterResult): MarvelCharacter
    fun getSmallImage(thumbnail: Thumbnail?):String?
    fun getLargeImage(thumbnail: Thumbnail?):String
    fun getMediumImage(thumbnail: Thumbnail?):String
}