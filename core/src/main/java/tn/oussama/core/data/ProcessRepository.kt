package tn.oussama.core.data

class ProcessRepository(private val dataSource: ProcessDataSource) {
    suspend fun findAllProcessesByUserID(userID: String) =  dataSource.findAllByUserID(userID)
    suspend fun findOneByID(processID: String) = dataSource.findOneByID(processID)
}