package tn.oussama.mp_android_bonita.framework.network.mappers

interface EntityMapper<Entity,DomainModel> {
    fun mapFromEntity(entity:Entity):DomainModel
    fun mapToEntiy(domainModel:DomainModel) : Entity
}