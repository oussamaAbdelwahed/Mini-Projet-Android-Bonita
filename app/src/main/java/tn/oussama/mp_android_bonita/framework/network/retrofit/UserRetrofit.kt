package tn.oussama.mp_android_bonita.framework.network.retrofit

import retrofit2.Response
import retrofit2.http.*
import tn.oussama.core.data.UserDataSource
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.ProcessNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.UserNetworkEntity

interface UserRetrofit {
    //username=walter.bates&password=bpm
    //each subsequest call must send all received cookie after login like JSESSIONID cookie ...
    @FormUrlEncoded
    @POST("bonita/loginservice")
    suspend fun  login( @Field("username")  username: String,
                        @Field("password")  password: String) : Response<Unit>

    @GET("bonita/logoutservice?redirect=false")
    suspend fun logout() : Response<Unit>

    @GET("bonita/API/identity/user")
    suspend fun getInformations(@Query("f", encoded = true) username:String): List<UserNetworkEntity>
}