package com.jcdelacueva.ernitakehome.data.repo

import com.jcdelacueva.ernitakehome.data.model.User
import com.jcdelacueva.ernitakehome.data.source.DataSource
import javax.inject.Inject

interface IUserService {
    suspend fun getUsers(): List<User>
}

class IUserServiceImpl @Inject constructor(private val dataSource: DataSource): IUserService {
    override suspend fun getUsers(): List<User> {
        return dataSource.getUsers()
    }
}
