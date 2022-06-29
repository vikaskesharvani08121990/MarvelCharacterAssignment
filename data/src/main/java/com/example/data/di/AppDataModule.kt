package com.example.data.di

import com.example.appcommon.utils.MD5HashKey
import com.example.data.mapper.DataMapper
import com.example.data.mapper.DataMapperImpl
import com.example.data.remote.api.MarvelAPI
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSource
import com.example.data.remote.datasource.MarvelCharacterRemoteDataSourceImpl
import com.example.data.repository.MarvelCharactersRepositoryImpl
import com.example.domain.repository.MarvelCharactersRepository
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.example.domain.usecase.GetMarvelCharacterDetailsUseCaseImpl
import com.example.domain.usecase.GetMarvelCharactersListUseCase
import com.example.domain.usecase.GetMarvelCharactersListUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppNetworkModule::class])
class AppDataModule {

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
    fun provideGetMarvelCharactersListUseCase(repository: MarvelCharactersRepository): GetMarvelCharactersListUseCase = GetMarvelCharactersListUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideGetMarvelCharacterDetailsUseCase(repository: MarvelCharactersRepository): GetMarvelCharacterDetailsUseCase = GetMarvelCharacterDetailsUseCaseImpl(repository)


    @Singleton
    @Provides
    fun getMDG(): MD5HashKey = MD5HashKey()

}