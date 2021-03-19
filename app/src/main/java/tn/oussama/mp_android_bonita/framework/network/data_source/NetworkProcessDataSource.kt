package tn.oussama.mp_android_bonita.framework.network.data_source

import android.content.res.Resources
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tn.oussama.core.data.DataState
import tn.oussama.core.data.ProcessDataSource
import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import java.lang.Exception

class NetworkProcessDataSource(val processRetrofit: ProcessRetrofit, val processNetworkMapper: ProcessNetworkMapper)  : ProcessDataSource {

    override suspend fun findAllByUserID(
        userID: Long,
        pageIndex: Int,
        perPage: Int
    ):  Flow<DataState<List<tn.oussama.core.domain.Process>>> = flow{
          emit(DataState.Loading)
          delay(2000)
          try {
             val networkProcesses =  processRetrofit.findAllByUserID(perPage = perPage,pageIndex=pageIndex,deployedByExpression = "deployedBy=$userID")
             val processes = processNetworkMapper.mapFromEntityList(networkProcesses)
             emit(DataState.Success(processes))
          } catch(e: Exception) {
             Log.i("************************ NETWORK PROCESSES LIST EXCEPTION *********************************",e.toString())
             emit(DataState.Error(e))
          }
    }

    override suspend fun findOneByID(processID: Long): DataState<Process> {
       try{
           val networkProcess = processRetrofit.findOneByID(processID)
           if(networkProcess.id > 0) return DataState.Success(processNetworkMapper.mapFromEntity(networkProcess))
           return   DataState.Error(Resources.NotFoundException("aucun processus ne porte l'id $processID et appartient a l'utilisateur connecté"))
       }catch (e: Exception) {
          Log.i("************************ NETWORK PROCESSES LIST EXCEPTION *********************************",e.toString())
          return DataState.Error(e)
       }
    }

}