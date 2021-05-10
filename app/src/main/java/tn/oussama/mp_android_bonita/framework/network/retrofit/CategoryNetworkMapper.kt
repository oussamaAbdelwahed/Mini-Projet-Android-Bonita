package tn.oussama.mp_android_bonita.framework.network.retrofit

import tn.oussama.core.domain.Category
import tn.oussama.mp_android_bonita.framework.network.CategoryNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.mappers.EntityMapper
import javax.inject.Inject

class CategoryNetworkMapper

@Inject
constructor(): EntityMapper<CategoryNetworkEntity, Category> {
    override fun mapFromEntity(entity: CategoryNetworkEntity): Category {
        return Category(
            id=entity.id,
            displayName = entity.displayName,
            name = entity.name,
            creationDate =  entity.creationDate,
            description = entity.description
        )
    }

    fun mapFromEntityList(l: List<CategoryNetworkEntity>): List<Category> {
        return l.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntiy(domainModel: Category): CategoryNetworkEntity{
        return CategoryNetworkEntity(
            id=domainModel.id,
            displayName = domainModel.displayName,
            name = domainModel.name,
            creationDate =  domainModel.creationDate,
            description = domainModel.description
        )
    }
}