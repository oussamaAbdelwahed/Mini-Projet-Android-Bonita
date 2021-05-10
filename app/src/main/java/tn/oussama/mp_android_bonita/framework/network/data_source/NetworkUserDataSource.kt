package tn.oussama.mp_android_bonita.framework.network.data_source

import android.util.Log
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import tn.oussama.core.data.DataState
import tn.oussama.core.data.UserDataSource
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit



class NetworkUserDataSource(val userRetrofit : UserRetrofit, val userNetworkMapper: UserNetworkMapper): UserDataSource {
    override suspend fun login(username: String, password: String): DataState<Boolean> {
        try {
             val r = userRetrofit.login(username, password)
            if(r.code()  in 200..300) {
                 Log.v("***LOGIN***","IN 200 .. 300 STATUS")
                return DataState.Success(true)
            }else{
                Log.v("***LOGIN***","NOT IN 200 .. 300 STATUS")
                return DataState.Success(false)
            }
        } catch (e: Exception) {
            Log.i("***************NETWORK_LOGIN_CALL***************",e.toString())
            return DataState.Error(e)
        }
    }

    override suspend fun logout(): DataState<Boolean> {
        try {
            val r =  userRetrofit.logout()
            if(r.code()  in 200..300) {
                Log.i("***************NETWORK_LOGOUT_CALL***************","BENNE LOGOUT")

                return DataState.Success(true)
            }else{
                Log.i("***************NETWORK_LOGOUT_CALL***************","NOT BENNE LOGOUT")
                return DataState.Success(false)
            }
        }catch(e: Exception) {
            Log.i("***************NETWORK_LOGIN_CALL***************",e.toString())
            return DataState.Error(e)
        }
    }

    override suspend fun getInformations(username: String): DataState<User> {
        try {
            val networkUser = userRetrofit.getInformations()
            return DataState.Success(userNetworkMapper.mapFromEntity(networkUser))
        }catch(e: Exception) {
            Log.i("***************NETWORK_LOGIN_CALL***************",e.toString())
            return DataState.Error(e)
        }
    }
}