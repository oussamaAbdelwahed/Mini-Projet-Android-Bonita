package tn.oussama.core.data

class ProcessRepository(private val dataSource: ProcessDataSource) {
    suspend fun findAllProcessesByUserID(userID: Long,perPage:Int=20,pageIndex:Int=0) =  dataSource.findAllByUserID(userID,perPage=perPage,pageIndex = pageIndex)
    suspend fun findOneByID(processID: Long) = dataSource.findOneByID(processID)
}