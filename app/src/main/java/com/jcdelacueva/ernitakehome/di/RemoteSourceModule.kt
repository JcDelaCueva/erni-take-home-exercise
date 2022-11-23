package com.jcdelacueva.ernitakehome.di

import com.jcdelacueva.ernitakehome.data.source.DataSource
import com.jcdelacueva.ernitakehome.data.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {

    @Binds
    abstract fun bindRemoteSource(remoteDataSource: RemoteDataSource): DataSource
}
