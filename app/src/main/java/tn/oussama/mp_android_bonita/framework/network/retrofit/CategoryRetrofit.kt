package tn.oussama.mp_android_bonita.framework.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import tn.oussama.mp_android_bonita.framework.network.CategoryNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.ProcessNetworkEntity

interface CategoryRetrofit {
 @GET("bonita/portal/custom-page/API/bpm/category")
    suspend fun  findAllCategories(
        @Query("c") perPage:Int=500,
        @Query("p") pageIndex:Int=0
    ): List<CategoryNetworkEntity>
}