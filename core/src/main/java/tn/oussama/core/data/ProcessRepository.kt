package tn.oussama.core.data

import tn.oussama.core.domain.ProcessInstanceSubmitResponse

class ProcessRepository(private val dataSource: ProcessDataSource) {
    suspend fun findAllProcessesByUserID(userID: Long,perPage:Int=20,pageIndex:Int=0) =  dataSource.findAllByUserID(userID,perPage=perPage,pageIndex = pageIndex)
    suspend fun findOneByID(processID: Long) = dataSource.findOneByID(processID)
    suspend fun findAllProcessesByUserIDAndCategory(userID: Long,categoryID:Long,perPage:Int=20,pageIndex:Int=0) = dataSource.findAllProcessesByUserIDAndCategory(userID,categoryID,perPage=perPage,pageIndex = pageIndex)
    suspend fun submitProcessInstance(processID: Long,body: HashMap<String,HashMap<String,Any>>)=  dataSource.submitProcessInstance(processID,body)

}