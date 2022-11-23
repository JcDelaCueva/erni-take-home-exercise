package com.jcdelacueva.ernitakehome.data.source

import com.jcdelacueva.ernitakehome.data.model.User

interface DataSource {

    suspend fun getUsers(): List<User>
}
