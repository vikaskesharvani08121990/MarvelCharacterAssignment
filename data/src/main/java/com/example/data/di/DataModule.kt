package com.example.data.di

import com.example.common.utils.MD5HashKey
import com.example.data.mapper.DataMapper
import com.example.data.mapper.DataMapperImpl
import com.example.data.remote.api.MarvelAPI
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSource
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSourceImpl
import com.example.data.repository.MarvelCharactersRepositoryImpl
import com.example.domain.repository.MarvelCharactersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideRemoteDataSource(apiService: MarvelAPI): MarvelCharacterRemoteDataSource =
        MarvelCharacterRemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideMarvelCharactersRepo(
        marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource,
        mapper: DataMapper,
        mD5HashKey: MD5HashKey
    ): MarvelCharactersRepository =
        MarvelCharactersRepositoryImpl(marvelCharacterRemoteDataSource, mapper, mD5HashKey)

    @Singleton
    @Provides
    fun provideMapper(): DataMapper = DataMapperImpl()

    @Singleton
    @Provides
    fun getMDG(): MD5HashKey = MD5HashKey()

}