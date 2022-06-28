package com.example.data.mapper

import com.example.common.utils.PORTRAIT_MEDIUM
import com.example.common.utils.PORTRAIT_SMALL
import com.example.common.utils.PORTRAIT_XLARGE
import com.example.data.entity.CharacterListResponse
import com.example.data.entity.MarvelCharacterResult
import com.example.data.entity.Thumbnail
import com.example.domain.model.MarvelCharacter

class DataMapperImpl : DataMapper {
    override fun mapToListOfMarvelCharacter(root: CharacterListResponse): List<MarvelCharacter> {
        return if (root.data?.results != null) root.data.results.let { result ->
            result.map { data -> mapMarvelCharacter(data) }
        } else emptyList()
    }

    override fun mapToCharacterDetails(root: CharacterListResponse): MarvelCharacter? {
        val list = if (root.data?.results != null)
            root.data.results.let { result ->
                result.map { data -> mapMarvelCharacter(data) }
            } else emptyList()

        return if (list.isEmpty()) null else list[0]

    }

    override fun mapMarvelCharacter(result: MarvelCharacterResult): MarvelCharacter {
        return MarvelCharacter(
            result.id ?: 0,
            result.name ?: "",
            description = result.description ?: "",
            image = getSmallImage(result.thumbnail) ?: "",
            bigImage = getLargeImage(result.thumbnail)
        )

    }

    override fun getSmallImage(thumbnail: Thumbnail?): String? {
        if (thumbnail != null && !thumbnail.path.isNullOrEmpty() && !thumbnail.extension.isNullOrEmpty())
            return thumbnail.path + PORTRAIT_SMALL + thumbnail.extension
        else return null
    }

    override fun getLargeImage(thumbnail: Thumbnail?): String {
        if (thumbnail != null && !thumbnail.path.isNullOrEmpty() && !thumbnail.extension.isNullOrEmpty())
            return thumbnail.path + PORTRAIT_XLARGE + thumbnail.extension
        else return ""
    }

    override fun getMediumImage(thumbnail: Thumbnail?): String {
        if (thumbnail != null && !thumbnail.path.isNullOrEmpty() && !thumbnail.extension.isNullOrEmpty())
            return thumbnail.path + PORTRAIT_MEDIUM + thumbnail.extension
        else return ""
    }

}