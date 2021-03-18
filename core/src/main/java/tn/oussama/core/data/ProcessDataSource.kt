package tn.oussama.core.data

interface ProcessDataSource {
    suspend fun findAllByUserID(userID: Long,pageIndex:Int=0,perPage:Int=10): List<Process>
    suspend fun findOneByID(processID: Long): Process
}