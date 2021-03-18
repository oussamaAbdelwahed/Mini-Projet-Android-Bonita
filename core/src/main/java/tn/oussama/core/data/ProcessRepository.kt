package tn.oussama.core.data

class ProcessRepository(private val dataSource: ProcessDataSource) {
    suspend fun findAllProcessesByUserID(userID: Long) =  dataSource.findAllByUserID(userID)
    suspend fun findOneByID(processID: Long) = dataSource.findOneByID(processID)
}