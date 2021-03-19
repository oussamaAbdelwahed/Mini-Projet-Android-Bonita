package tn.oussama.mp_android_bonita.framework.network.retrofit

import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.framework.network.ProcessNetworkEntity
import tn.oussama.mp_android_bonita.framework.network.mappers.EntityMapper
import java.util.*
import javax.inject.Inject

class ProcessNetworkMapper
@Inject
constructor():EntityMapper<ProcessNetworkEntity,Process>
{
    override fun mapFromEntity(entity: ProcessNetworkEntity): Process {
      return Process(
         id = entity.id,
         icon= entity?.icon,
         deploymentDate= entity.deploymentDate,
         description=if(entity.displayDescription.isEmpty()) entity.description else entity.displayDescription  ,
         activationState=entity.activationState,
         name=if(entity.name.isEmpty()) entity.displayName else entity.name,
         deployedBy=entity.deployedBy,
         actorInitiatorId=entity.actorInitiatorId,
         lastUpdateDate=entity.lastUpdateDate,
         configurationState=entity.configurationState,
         version=entity.version,
      )
    }

    fun mapFromEntityList(l:List<ProcessNetworkEntity>): List<Process> {
        return l.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntiy(domainModel: Process): ProcessNetworkEntity {
      return ProcessNetworkEntity(
          id = domainModel.id,
          icon= domainModel.icon,
          deploymentDate = domainModel.deploymentDate,
          displayDescription = domainModel.description,
          activationState=domainModel.activationState,
          displayName= domainModel.name,
          deployedBy=domainModel.deployedBy,
          actorInitiatorId=domainModel.actorInitiatorId,
          lastUpdateDate=domainModel.lastUpdateDate,
          configurationState=domainModel.configurationState,
          version=domainModel.version
      )
    }
}