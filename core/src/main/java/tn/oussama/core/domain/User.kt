package tn.oussama.core.domain

import java.io.Serializable

//to finish IMPL when reading bonita user entity DOCS
data class User
    (
     val id: Long,
     val userName: String,
     val isGuestUser: Boolean,
     val isTechnicalUser: Boolean,
    ): Serializable
{}