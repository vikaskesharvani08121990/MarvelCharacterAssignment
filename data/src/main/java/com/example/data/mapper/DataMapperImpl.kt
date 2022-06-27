package com.example.data.mapper

import com.example.common.utils.PORTRAIT_MEDIUM
import com.example.common.utils.PORTRAIT_SMALL
import com.example.common.utils.PORTRAIT_XLARGE
import com.example.data.entity.DataCharacterListDTO
import com.example.data.entity.MarvelCharacterResult
import com.example.data.entity.Thumbnail
import com.example.domain.model.DomainMatcherCharacterListResponse
import com.example.domain.model.MarvelCharacter

class DataMapperImpl:DataMapper {
    override fun mapToRootObject(root :DataCharacterListDTO): DomainMatcherCharacterListResponse {
        return DomainMatcherCharacterListResponse(root.code?:0,root.status?:"",
            if(root.data?.results != null) root.data.results.let {
                    result->result.map {data->mapMarvelCharacter(data)  }
            } else emptyList())
    }

    override fun mapMarvelCharacter(result:MarvelCharacterResult): MarvelCharacter {
        return MarvelCharacter(result.id?:0,result.name?:"",description=result.description?:"", image = getSmallImage(result.thumbnail)?:"", bigImage = getMediumImage(result.thumbnail))

    }

    override fun getSmallImage(thumbnail: Thumbnail?): String? {
        if(thumbnail!=null&&!thumbnail.path.isNullOrEmpty()&&!thumbnail.extension.isNullOrEmpty())
            return thumbnail.path+ PORTRAIT_SMALL +thumbnail.extension
        else return null
    }

    override fun getLargeImage(thumbnail: Thumbnail?): String {
        if(thumbnail!=null&&!thumbnail.path.isNullOrEmpty()&&!thumbnail.extension.isNullOrEmpty())
            return thumbnail.path+ PORTRAIT_XLARGE +thumbnail.extension
        else return ""
    }

    override fun getMediumImage(thumbnail: Thumbnail?): String {
        if(thumbnail!=null&&!thumbnail.path.isNullOrEmpty()&&!thumbnail.extension.isNullOrEmpty())
            return thumbnail.path+ PORTRAIT_MEDIUM +thumbnail.extension
        else return ""
    }

}