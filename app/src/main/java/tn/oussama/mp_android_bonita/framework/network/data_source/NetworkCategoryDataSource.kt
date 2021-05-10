package tn.oussama.mp_android_bonita.framework.network.data_source

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tn.oussama.core.data.CategoryDataSource
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.Category
import tn.oussama.mp_android_bonita.framework.network.retrofit.CategoryNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.CategoryRetrofit

import java.lang.Exception

class NetworkCategoryDataSource (val categoryRetrofit: CategoryRetrofit, val categoryNetworkMapper: CategoryNetworkMapper) :CategoryDataSource{
    override suspend fun findAllCategories() :  Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        try {
            val networkCategories =  categoryRetrofit.findAllCategories()
            val categories = categoryNetworkMapper.mapFromEntityList(networkCategories)
            emit(DataState.Success(categories))
        } catch(e: Exception) {
            Log.i("************************ NETWORK CATEGORIES LIST EXCEPTION *********************************",e.toString())
            emit(DataState.Error(e))
        }
    }
}