package tn.oussama.core.interactors

import tn.oussama.core.data.UserRepository

class LoginUser(private val userRepository: UserRepository){
    suspend operator fun invoke(userame: String, password: String) =  userRepository.loginUser(userame,password)
}