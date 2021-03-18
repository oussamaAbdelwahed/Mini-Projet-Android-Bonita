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
import tn.oussama.core.interactors.GetUserInformations
import tn.oussama.core.interactors.LoginUser
import tn.oussama.mp_android_bonita.framework.network.data_source.NetworkUserDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRetrofit : UserRetrofit,private val userNetworkMapper: UserNetworkMapper): ViewModel() {
    var errorMsg :String=""
    private val isLoggedIn_ = MutableLiveData<Boolean>()

    fun isLoggedIn():LiveData<Boolean> = isLoggedIn_

    fun login(username:String,password:String)  {
       val loginUser = LoginUser(UserRepository(NetworkUserDataSource(userRetrofit,userNetworkMapper)))

       //the basic building block of spawning a thread from current thread to be executed in parallel without blocking the current one
       viewModelScope.launch {
           when(loginUser(username,password)) {
               is DataState.Error -> {
                   Log.i("***************LOGIN PROCESS******************","HERE 1")
                   isLoggedIn_.value = false
                   errorMsg  = "username ou mot de passe incorrectes!"
               } else -> {
                   Log.i("***************LOGIN PROCESS******************","HERE 2")
                   val userInformations = GetUserInformations(UserRepository(NetworkUserDataSource(userRetrofit,userNetworkMapper)))
                   launch {
                     when(userInformations("userName="+username)) {
                        is DataState.Error -> {
                            Log.i("***************LOGIN PROCESS******************","HERE 3")
                            isLoggedIn_.value = false
                            errorMsg = "une erreur est survenue, veuillez ressayer!"
                        } else -> {
                            Log.i("***************LOGIN PROCESS******************","HERE 4")
                            isLoggedIn_.value = true
                            errorMsg = ""
                        }
                      }
                   }
               }
           }
       }
   }

}