package tn.oussama.core.data

import tn.oussama.core.domain.User

class UserRepository(private val dataSource: UserDataSource) {
    suspend fun loginUser(username: String,password:String) =  dataSource.login(username,password)
    suspend fun logoutUser(sessionID: String?) = dataSource.logout(sessionID)
}