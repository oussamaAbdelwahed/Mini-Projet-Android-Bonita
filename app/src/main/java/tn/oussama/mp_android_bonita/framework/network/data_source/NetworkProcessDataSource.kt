package tn.oussama.mp_android_bonita.framework.network.data_source

import tn.oussama.core.data.ProcessDataSource
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import javax.inject.Inject


class NetworkProcessDataSource  : ProcessDataSource {
    //HERE INJECT PROCESS RETROFIT INSTANCE
    override suspend fun findAllByUserID(
        userID: Long,
        pageIndex: Int,
        perPage: Int
    ): List<Process> {
        TODO("Not yet implemented")
    }

    override suspend fun findOneByID(processID: Long): Process {
        TODO("Not yet implemented")
    }

}