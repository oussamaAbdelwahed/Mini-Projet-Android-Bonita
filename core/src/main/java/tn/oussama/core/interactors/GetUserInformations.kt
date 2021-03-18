package tn.oussama.core.interactors

import tn.oussama.core.data.UserRepository

class GetUserInformations (private val userRepository: UserRepository){
    suspend operator fun invoke(username: String) =  userRepository.getInformations(username)
}
