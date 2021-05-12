package tn.oussama.core.interactors

import tn.oussama.core.data.ProcessRepository

class PostProcessInstance (private val processRepository: ProcessRepository) {
    suspend operator fun invoke(processID: Long,body: HashMap<String,HashMap<String,Any>>) = processRepository.submitProcessInstance(processID,body)
}