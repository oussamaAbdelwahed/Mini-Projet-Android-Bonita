package tn.oussama.core.data

import tn.oussama.core.domain.User

interface UserDataSource {
      suspend fun login(username: String,password:String): DataState<Boolean>
      suspend fun logout(): DataState<Boolean>


      //network user http://localhost:8080/bonita/API/identity/user?f=userName=yourUsername
      suspend fun getInformations(username:String): DataState<User>
}