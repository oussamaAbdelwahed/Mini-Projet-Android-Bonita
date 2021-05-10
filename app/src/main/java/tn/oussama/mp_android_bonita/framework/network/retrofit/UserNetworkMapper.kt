package tn.oussama.mp_android_bonita.framework.network.retrofit

import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.UserNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.mappers.EntityMapper
import javax.inject.Inject

class UserNetworkMapper

: EntityMapper<UserNetworkEntity, User>
{

    /*
    * {
    "copyright": "Bonitasoft © 2020",
    "is_guest_user": "false",
    "branding_version": "2021.1",
    "user_id": "3",
    "user_name": "etud1.l3sil",
    "session_id": "2279108163884516926",
    "conf": "[\"597792899045B812E404921961FEC9B3AF57ACB2\",\"5A6FED1E4284C314B0A21553F92FA93533C90EAD\",\"5D5E31B42806EB533B649E449F51E918522C609A\"]",
    "is_technical_user": "false",
    "version": "7.12.1"
}
    * */
    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            id = entity.id,
            userName = entity.userName,
            isGuestUser = entity.isGuestUser,
            isTechnicalUser = entity.isTechnicalUser
        )
    }

    override fun mapToEntiy(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            id = domainModel.id,
            userName = domainModel.userName,
            isGuestUser = domainModel.isGuestUser,
            isTechnicalUser = domainModel.isTechnicalUser
        )
    }
}