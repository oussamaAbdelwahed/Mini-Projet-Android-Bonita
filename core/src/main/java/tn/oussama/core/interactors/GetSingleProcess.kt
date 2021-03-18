package tn.oussama.core.interactors

import tn.oussama.core.data.ProcessRepository

class GetSingleProcess(private val processRepository: ProcessRepository) {
    suspend operator fun invoke(processID: Long) = processRepository.findOneByID(processID)
}