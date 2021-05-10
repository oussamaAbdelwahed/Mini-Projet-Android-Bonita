package tn.oussama.core.data

class CategoryRepository(private val dataSource: CategoryDataSource) {
    suspend fun findAllCategories() =  dataSource.findAllCategories()
}