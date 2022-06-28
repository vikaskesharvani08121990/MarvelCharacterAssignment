package com.example.data.repository
import com.example.common.utils.MD5HashKey
import com.example.common.utils.network.NetworkStatus
import com.example.data.mapper.DataMapper
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterList
import com.example.domain.repository.GetMarvelCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMarvelCharactersRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
private val mapper: DataMapper,
private val mD5HashKey: MD5HashKey):
    GetMarvelCharactersRepository {
    override suspend fun getMarvelCharacters(
        publicKey: String,
        privateKey: String,
        time: Long
    ): NetworkStatus<CharacterList> {
        var hash= mD5HashKey.getHash(publicKey,privateKey,time)
       return withContext(Dispatchers.IO){

          var response= remoteDataSource.getMarvelCharacters(publicKey,hash,time)
           when(response){
              is  NetworkStatus.Success->{
                  return@withContext NetworkStatus.Success(data=mapper.mapToRootObject(response.data!!))
              }
               is NetworkStatus.Loading ->{
                   return@withContext NetworkStatus.Loading()
               }
               else->{
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
    ): NetworkStatus<CharacterDetails> {
        var hash= mD5HashKey.getHash(publicKey,privateKey,time)
        return withContext(Dispatchers.IO){

            var response= remoteDataSource.getMarvelCharacterByCharacterId(publicKey,hash,time,characterId)
            when(response){
                is  NetworkStatus.Success->{
                    return@withContext NetworkStatus.Success(data=
                    mapper.mapToRootDetails(response.data!!))
                }
                is NetworkStatus.Loading ->{
                    return@withContext NetworkStatus.Loading()
                }
                else->{
                    return@withContext NetworkStatus.Error(errorMessage = response.errorMessage)
                }

            }

        }
    }


}
