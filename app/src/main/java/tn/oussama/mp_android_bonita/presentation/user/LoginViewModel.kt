package tn.oussama.mp_android_bonita.presentation.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tn.oussama.core.data.DataState
import tn.oussama.core.data.UserRepository
import tn.oussama.core.domain.User
import tn.oussama.core.interactors.GetUserInformations
import tn.oussama.core.interactors.LoginUser
import tn.oussama.core.interactors.LogoutUser
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkUserDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit
import tn.oussama.mp_android_bonita.framework.utils.clearUserSession
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRetrofit : UserRetrofit,private val userNetworkMapper: UserNetworkMapper): ViewModel() {
    var errorMsg :String=""
    var user: User? = null
    private val isLoggedIn_ = MutableLiveData<String>("INIT")


    fun isLoggedIn():LiveData<String> = isLoggedIn_

    fun login(username:String,password:String) {
       val loginUser = LoginUser(UserRepository(NetworkUserDataSource(userRetrofit,userNetworkMapper)))
       //the basic building block of spawning a thread from current thread to be executed in parallel without blocking the current one

        viewModelScope.launch {
           val l = loginUser(username,password)
           when(l) {
               is DataState.Error -> {
                   errorMsg = "une erreur est survenue, veuillez ressayer!"
                   isLoggedIn_.value = "FALSE"
               }  is DataState.Success<Boolean> -> {
                   if(l.data) {
                       val userInformations = GetUserInformations(
                           UserRepository(
                               NetworkUserDataSource(
                                   userRetrofit,
                                   userNetworkMapper
                               )
                           )
                       )
                       launch {
                           val u = userInformations("userName=" + username)
                           when (u) {
                               is DataState.Error -> {
                                   errorMsg = "une erreur est survenue, veuillez ressayer!"
                                   isLoggedIn_.value = "FALSE"
                               }is DataState.Success<User> -> {
                                   user = u.data
                                   errorMsg = ""
                                   isLoggedIn_.value = "TRUE"
                               }
                           }
                       }
                   }else{
                       errorMsg  = "username ou mot de passe incorrectes!"
                       isLoggedIn_.value = "FALSE"
                   }
               }
           }
       }
   }


    fun logout() {
        val logoutUser = LogoutUser(UserRepository(NetworkUserDataSource(userRetrofit,userNetworkMapper)))
        viewModelScope.launch {
           val l = logoutUser()
            clearUserSession()
        }
    }

}