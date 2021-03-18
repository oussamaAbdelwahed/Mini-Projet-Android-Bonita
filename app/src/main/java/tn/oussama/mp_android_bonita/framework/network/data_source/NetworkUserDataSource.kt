package tn.oussama.mp_android_bonita.framework.network.data_source

import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import tn.oussama.core.data.DataState
import tn.oussama.core.data.UserDataSource
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit



class NetworkUserDataSource(val userRetrofit : UserRetrofit, val userNetworkMapper: UserNetworkMapper): UserDataSource {
 /*   @Inject
    lateinit var userRetrofit : UserRetrofit

    @Inject
    lateinit var  userNetworkMapper: UserNetworkMapper*/

    override suspend fun login(username: String, password: String): DataState<Boolean> {
        try {
            userRetrofit.login(username, password)
            return DataState.Success(true)
        } catch (e: Exception) {
            Log.i("***************NETWORK_LOGIN_CALL***************",e.toString())
            return DataState.Error(e)
        }
    }

    override suspend fun logout(): DataState<Boolean> {
        try {
            userRetrofit.logout()
            return DataState.Success(true)
        }catch(e: Exception) {
            return DataState.Error(e)
        }
    }

    override suspend fun getInformations(username: String): DataState<User> {
        try {
            val networkUser = userRetrofit.getInformations(username)
            return DataState.Success(userNetworkMapper.mapFromEntity(networkUser[0]))
        }catch(e: Exception) {
            Log.i("***************NETWORK_LOGIN_CALL***************",e.toString())
            return DataState.Error(e)
        }
    }
}