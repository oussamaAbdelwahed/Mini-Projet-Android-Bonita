package tn.oussama.mp_android_bonita.framework.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tn.oussama.core.domain.ProcessContract
import tn.oussama.mp_android_bonita.framework.network.ProcessNetworkEntity

interface ProcessRetrofit {
    //https://digitalisi.tn/bonita/login.jsp?redirectUrl=%2Fbonita%2Fportal%2Fhomepage
    /*
    curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonita/API/bpm/process?c=10&p=0&f=deployedBy=4'
     //@Query("group") String group
    * @GET("api/Profiles/GetProfile?id={id}")
    Call<UserProfile> getUser(@Path("id") String id, @Header("Authorization") String authHeader);
    * */
    //must add userid query param
    //@GET("bonita/API/bpm/process?c={perPage}&p={pageIndex}&f=deployedBy={id}")
    //this is the true endpoint
    //http://localhost:8080/bonita/API/bpm/process?p=0&o=version%20desc&f=activationState=ENABLED
    @GET("bonita/API/bpm/process")
    suspend fun  findAllByUserID(
        @Query("c") perPage:Int,
        @Query("p") pageIndex:Int,
        @Query("o") order:String="version desc",
        @Query("f",encoded = true) deployedByExpression:String,
        @Query("f",encoded = true) state:String="activationState=ENABLED"

    ): List<ProcessNetworkEntity>

    //this is the true endpoint to get from it FORM FIELDS bonita/API/bpm/process/{id}/contract
    //example prof bonita/API/bpm/process/7370685993814134092/contract

    @GET("bonita/API/bpm/process/{id}/contract")
    suspend fun findOneByID(@Path("id") processID: Long): ProcessContract

    @GET("bonita/API/bpm/process")
    suspend fun findAllProcessesByUserIDAndCategory(
        @Query("c") perPage:Int,
        @Query("p") pageIndex:Int,
        @Query("o") order:String="version desc",
        @Query("f",encoded = true) deployedByExpression:String,
        @Query("f",encoded = true) categoryExpression:String,
        @Query("f",encoded = true) state:String="activationState=ENABLED"

    ): List<ProcessNetworkEntity>

}