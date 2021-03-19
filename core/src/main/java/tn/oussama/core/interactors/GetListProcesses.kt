package tn.oussama.core.interactors

import tn.oussama.core.data.ProcessRepository

class GetListProcesses(private val processRepository: ProcessRepository) {
    suspend operator fun invoke(userID: Long,pageIndex: Int=0, perPage:Int=20) = processRepository.findAllProcessesByUserID(userID=userID,pageIndex=pageIndex,perPage =  perPage)
}