package com.jcdelacueva.ernitakehome.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdelacueva.ernitakehome.data.filterDuplicatedUser
import com.jcdelacueva.ernitakehome.data.repo.IUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: IUserService): ViewModel() {

    private val mutableState: MutableSharedFlow<MainState> = MutableSharedFlow()
    val state = mutableState.asSharedFlow()

    fun downloadUsers() {
        viewModelScope.launch {
            try {
                mutableState.emit(MainState.ShowLoading)
                val users = filterDuplicatedUser(repo.getUsers())
                mutableState.emit(MainState.LoadUsers(users))
            } catch (e: Exception) {
                e.printStackTrace()
                mutableState.emit((MainState.ShowError(e)))
            }
        }
    }
}
