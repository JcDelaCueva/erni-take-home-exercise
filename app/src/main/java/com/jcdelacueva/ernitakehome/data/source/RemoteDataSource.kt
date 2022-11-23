package com.jcdelacueva.ernitakehome.data.source

import com.jcdelacueva.ernitakehome.data.APIInterface
import com.jcdelacueva.ernitakehome.data.model.User
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: APIInterface) : DataSource {
    override suspend fun getUsers(): List<User> {
        return apiInterface.getUsers()
    }
}
