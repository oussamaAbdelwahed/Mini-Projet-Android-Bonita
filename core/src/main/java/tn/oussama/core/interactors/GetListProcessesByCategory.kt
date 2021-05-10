package tn.oussama.core.interactors

import tn.oussama.core.data.ProcessRepository

class GetListProcessesByCategory(private val processRepository: ProcessRepository) {
        suspend operator fun invoke(userID: Long,categoryID: Long,pageIndex: Int=0, perPage:Int=20) = processRepository.findAllProcessesByUserIDAndCategory(userID=userID,categoryID=categoryID,pageIndex=pageIndex,perPage =  perPage)
}