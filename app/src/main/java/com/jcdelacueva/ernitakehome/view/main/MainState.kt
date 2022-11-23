package com.jcdelacueva.ernitakehome.view.main

import com.jcdelacueva.ernitakehome.data.model.User

sealed class MainState {
    object ShowLoading : MainState()
    data class LoadUsers(val users: List<User>) : MainState()
    data class ShowError(val error: Exception): MainState()
}
