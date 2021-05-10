package tn.oussama.core.data

import kotlinx.coroutines.flow.Flow
import tn.oussama.core.domain.Category


interface CategoryDataSource {
    suspend fun findAllCategories(): Flow<DataState<List<Category>>>
}