package tn.oussama.mp_android_bonita.framework.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tn.oussama.mp_android_bonita.framework.network.ProcessNetworkEntity

interface ProcessRetrofit {

    /*
    curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonita/API/bpm/process?c=10&p=0&f=deployedBy=4'
     //@Query("group") String group
    * @GET("api/Profiles/GetProfile?id={id}")
    Call<UserProfile> getUser(@Path("id") String id, @Header("Authorization") String authHeader);
    * */
    //must add userid query param
    @GET("bonita/API/bpm/process?c={perPage}&p={pageIndex}&f=deployedBy={id}")
    suspend fun  findAllByUserID(@Path("id") userID: Long,@Path("pageIndex") pageIndex:Int=0,@Path("perPage") perPage:Int=10): List<ProcessNetworkEntity>

    @GET("bonita/API/bpm/process/{id}")
    suspend fun findOneByID(@Path("id") processID: Long): Process


}