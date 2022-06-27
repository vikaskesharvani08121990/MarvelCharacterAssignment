package com.example.data.di


import com.example.common.utils.MD5HashKey
import com.example.data.mapper.DataMapper
import com.example.data.mapper.DataMapperImpl
import com.example.data.remote.api.MarvelAPI
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.data.repository.GetMarvelCharactersRepositoryImpl
import com.example.domain.repository.GetMarvelCharactersRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideRemoteDataSource(apiService: MarvelAPI): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }


    @Singleton
    @Provides
    fun provideMarvelCharactersRepo(
        remoteDataSource: RemoteDataSource,
        mapper: DataMapper,
        mD5HashKey: MD5HashKey): GetMarvelCharactersRepository{
        return GetMarvelCharactersRepositoryImpl(remoteDataSource,mapper,mD5HashKey)
    }

    @Singleton
    @Provides
     fun provideMapper(): DataMapper{
         return DataMapperImpl()
     }

    @Singleton
    @Provides
    fun getMDG():MD5HashKey{
        return MD5HashKey()
    }


}