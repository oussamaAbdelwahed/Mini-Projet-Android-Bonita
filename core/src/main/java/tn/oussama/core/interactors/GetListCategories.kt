package tn.oussama.core.interactors

import tn.oussama.core.data.CategoryRepository
import tn.oussama.core.data.ProcessRepository

class GetListCategories(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke() = categoryRepository.findAllCategories()
}