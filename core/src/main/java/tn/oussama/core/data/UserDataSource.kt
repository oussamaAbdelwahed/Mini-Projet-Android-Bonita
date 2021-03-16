package tn.oussama.core.data

import tn.oussama.core.domain.User

interface UserDataSource {
      suspend fun login(username: String,password:String): User?
      suspend fun logout(sessionID: String?): Boolean
}