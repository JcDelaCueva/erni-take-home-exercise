package com.jcdelacueva.ernitakehome.view.details

import com.jcdelacueva.ernitakehome.data.model.User

sealed class DetailsState {
    data class ShowUserInfo(val user : User) : DetailsState()
}
