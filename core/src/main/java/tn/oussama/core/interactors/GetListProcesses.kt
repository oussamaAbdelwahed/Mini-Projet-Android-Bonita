package tn.oussama.core.interactors

import tn.oussama.core.data.ProcessRepository

class GetListProcesses(private val processRepository: ProcessRepository) {
    suspend operator fun invoke(userID: Long) = processRepository.findAllProcessesByUserID(userID)
}