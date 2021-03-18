package tn.oussama.mp_android_bonita.framework.network.retrofit

import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.UserNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.mappers.EntityMapper
import javax.inject.Inject

class UserNetworkMapper

: EntityMapper<UserNetworkEntity, User>
{
    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            id = entity.id,
            icon = entity.icon,
            jobTitle = entity.jobTitle,
            userName = entity.userName,
            firstName = entity.userName,
            lastName = entity.userName
        )
    }

    override fun mapToEntiy(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            id = domainModel.id,
            icon = domainModel.icon,
            jobTitle = domainModel.jobTitle,
            userName = domainModel.userName,
            firstName = domainModel.userName,
            lastName = domainModel.userName
        )
    }
}