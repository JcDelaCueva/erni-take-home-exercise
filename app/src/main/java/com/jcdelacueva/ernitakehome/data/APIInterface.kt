package com.jcdelacueva.ernitakehome.data

import com.jcdelacueva.ernitakehome.Constants
import com.jcdelacueva.ernitakehome.data.model.User
import retrofit2.http.GET

interface APIInterface {

    @GET(Constants.TEST_JSON)
    suspend fun getUsers(): List<User>
}
