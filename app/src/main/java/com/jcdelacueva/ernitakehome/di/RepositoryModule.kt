package com.jcdelacueva.ernitakehome.di

import com.jcdelacueva.ernitakehome.data.repo.IUserService
import com.jcdelacueva.ernitakehome.data.repo.IUserServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userServiceImpl: IUserServiceImpl): IUserService
}
