package tn.oussama.core.data

import kotlinx.coroutines.flow.Flow
import tn.oussama.core.domain.ProcessContract

interface ProcessDataSource {
    suspend fun findAllByUserID(userID: Long,pageIndex:Int=0,perPage:Int=10): Flow<DataState<List<tn.oussama.core.domain.Process>>>
    suspend fun findOneByID(processID: Long): DataState<ProcessContract>
    suspend fun findAllProcessesByUserIDAndCategory(userID: Long,categoryID:Long ,pageIndex:Int=0,perPage:Int=10): Flow<DataState<List<tn.oussama.core.domain.Process>>>
}