package com.jcdelacueva.ernitakehome.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdelacueva.ernitakehome.data.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val mutableUser = MutableLiveData<User>()
    val user: LiveData<User> get() = mutableUser

    fun setUserInfo(user: User) {
        mutableUser.value = user
    }
}
