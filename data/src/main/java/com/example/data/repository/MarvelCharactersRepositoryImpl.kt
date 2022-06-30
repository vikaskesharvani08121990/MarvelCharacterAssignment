package com.example.data.repository

import com.example.appcommon.utils.MD5HashKey
import com.example.appcommon.utils.NetworkResponse
import com.example.data.mapper.DataMapper
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSource
import com.example.domain.model.MarvelCharacter
import com.example.domain.repository.MarvelCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelCharactersRepositoryImpl(
    private val marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource,
    private val mapper: DataMapper,
    private val mD5HashKey: MD5HashKey
) : MarvelCharactersRepository {
    override suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkResponse<List<MarvelCharacter>> {
        val hash = mD5HashKey.getHash(publicKey, privateKey, time)
        return withContext(Dispatchers.IO) {

            when (val response =
                marvelCharacterRemoteDataSource.getMarvelCharacters(publicKey, hash, time)) {
                is NetworkResponse.Success -> {
                    return@withContext NetworkResponse.Success(
                        data = mapper.mapToListOfMarvelCharacter(
                            response.data!!
                        )
                    )
                }
                else -> {
                    return@withContext NetworkResponse.Error(errorMessage = response.errorMessage)
                }

            }

        }
    }

    override suspend fun getMarvelCharacterById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ): NetworkResponse<MarvelCharacter> {
        val hash = mD5HashKey.getHash(publicKey, privateKey, time)
        return withContext(Dispatchers.IO) {

            val response =
                marvelCharacterRemoteDataSource.getMarvelCharacterByCharacterId(
                    publicKey,
                    hash,
                    time,
                    characterId
                )
            when (response) {
                is NetworkResponse.Success -> {
                    return@withContext NetworkResponse.Success(
                        data =
                        mapper.mapToCharacterDetails(response.data!!)
                    )
                }
                else -> {
                    return@withContext NetworkResponse.Error(errorMessage = response.errorMessage)
                }

            }

        }
    }


}
