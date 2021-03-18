package tn.oussama.core.interactors

import tn.oussama.core.data.UserRepository

class LogoutUser(private val userRepository: UserRepository) {
    suspend operator fun invoke() =  userRepository.logoutUser()
}