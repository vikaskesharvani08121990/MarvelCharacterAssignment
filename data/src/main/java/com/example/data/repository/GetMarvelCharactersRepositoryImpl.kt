package com.example.data.repository

import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.data.mapper.DataMapper
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.model.MarvelCharacter
import com.example.domain.repository.GetMarvelCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMarvelCharactersRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: DataMapper,
    private val mD5HashKey: MD5HashKey
) :
    GetMarvelCharactersRepository {
    override suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<List<MarvelCharacter>> {
        val hash = mD5HashKey.getHash(publicKey, privateKey, time)
        return withContext(Dispatchers.IO) {

            when (val response = remoteDataSource.getMarvelCharacters(publicKey, hash, time)) {
                is NetworkStatus.Success -> {
                    return@withContext NetworkStatus.Success(
                        data = mapper.mapToListOfMarvelCharacter(
                            response.data!!
                        )
                    )
                }
                is NetworkStatus.Loading -> {
                    return@withContext NetworkStatus.Loading()
                }
                else -> {
                    return@withContext NetworkStatus.Error(errorMessage = response.errorMessage)
                }

            }

        }
    }

    override suspend fun getMarvelCharacterById(
        publicKey: String,
        privateKey: String,
        time: Long,
        characterId: Int
    ): NetworkStatus<MarvelCharacter> {
        val hash = mD5HashKey.getHash(publicKey, privateKey, time)
        return withContext(Dispatchers.IO) {

            val response =
                remoteDataSource.getMarvelCharacterByCharacterId(publicKey, hash, time, characterId)
            when (response) {
                is NetworkStatus.Success -> {
                    return@withContext NetworkStatus.Success(
                        data =
                        mapper.mapToCharacterDetails(response.data!!)
                    )
                }
                is NetworkStatus.Loading -> {
                    return@withContext NetworkStatus.Loading()
                }
                else -> {
                    return@withContext NetworkStatus.Error(errorMessage = response.errorMessage)
                }

            }

        }
    }


}
