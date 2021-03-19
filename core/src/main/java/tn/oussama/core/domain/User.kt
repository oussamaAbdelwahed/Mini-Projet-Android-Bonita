package tn.oussama.core.domain

import java.io.Serializable

//to finish IMPL when reading bonita user entity DOCS
data class User
    (
     val id: Long,
     val jobTitle: String,
     val userName: String,
     val firstName: String,
     val lastName: String,
     val icon: String
    ): Serializable
{}