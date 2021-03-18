package tn.oussama.core.data



class UserRepository(private val dataSource: UserDataSource) {
    suspend fun loginUser(username: String,password:String) =  dataSource.login(username,password)
    suspend fun logoutUser() = dataSource.logout()
    suspend fun getInformations(username: String) = dataSource.getInformations(username)
}