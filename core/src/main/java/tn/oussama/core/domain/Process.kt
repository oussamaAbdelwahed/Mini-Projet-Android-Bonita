package tn.oussama.core.domain

import java.util.*

//to finish IMPL when reading bonita user entity DOCS
data class Process
    (
      var id: Long=0,
      var icon: String="",
      var deploymentDate: Date,
      var description: String="",
      var activationState: String="",
      var name: String="",
      var deployedBy: Int=0,
      var actorInitiatorId: Long=0,
      var lastUpdateDate: Date?=null,
      var configurationState: String="",
      var version: String="",
    )
{
}