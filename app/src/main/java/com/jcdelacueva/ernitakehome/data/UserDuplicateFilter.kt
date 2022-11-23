package com.jcdelacueva.ernitakehome.data

import com.jcdelacueva.ernitakehome.data.model.User

fun filterDuplicatedUser(users: List<User>) = users.distinctBy {
    it.id
}