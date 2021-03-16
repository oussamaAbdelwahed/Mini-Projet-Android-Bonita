package tn.oussama.core.data

interface ProcessDataSource {
    suspend fun findAllByUserID(userID: String): List<Process>
    suspend fun findOneByID(processID: String): Process
}