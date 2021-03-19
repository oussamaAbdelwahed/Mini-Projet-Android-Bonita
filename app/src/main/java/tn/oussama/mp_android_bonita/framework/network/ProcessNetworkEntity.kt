package tn.oussama.mp_android_bonita.framework.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

///GENERAL FORM OF A GET REQUEST WITH FILTER / ORDER/ PAGINATION...
//http://.../API/{API_name}/{resource_name}?p={page}&c={count}&o={order}&s={query}&f={filter_name}={filter_value}&f=...

//GETTING ALL PROCESSES OF LOGGED ON USER --->
//curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonita/API/bpm/process?c=10&p=0&f=deployedBy=4'
//@Query("group") String group

//GETTING A GIVEN PROCESS BY ID
//curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonitaAPI/bpm/process/:processId'

//FOR GETTING UI COMPONENTS OF PROCESS FORM AND THEIR  ASSOCIATED CONSTRAINTS IN THAT FORM {"inputs":[],"constraints":[]}
//curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonita/API/bpm/process/5967209955634004729/contract'
class ProcessNetworkEntity
    (
      @SerializedName("id")
      @Expose
      var id: Long,

      @SerializedName("icon")
      @Expose
      var icon: String?,

      @SerializedName("displayDescription")
      @Expose
      var displayDescription: String,

      @SerializedName("deploymentDate")
      @Expose
      var deploymentDate: Date,

      @SerializedName("description")
      @Expose
      var description: String="",

      @SerializedName("activationState")
      @Expose
      var activationState: String,

      @SerializedName("name")
      @Expose
      var name: String="",

      //this id actually should be used to
      @SerializedName("deployedBy")
      @Expose
      var deployedBy: Int,

      @SerializedName("displayName")
      @Expose
      var displayName: String,

      @SerializedName("actorinitiatorid")
      @Expose
      var actorInitiatorId: Long,

      @SerializedName("last_update_date")
      @Expose
      var lastUpdateDate: Date?,

      @SerializedName("configurationState")
      @Expose
      var configurationState: String,

      @SerializedName("version")
      @Expose
      var version: String,
    )
{
}

/*  JSON RESPONSE FORM FROM BONITA REST API
  "id":"the identifier of the process definition (long)",
  "icon":"icon path (string)",
  "displayDescription":"the human readable activity description (string)",
  "deploymentDate":"the date when the process definition was deployed (date)",
  "description":"the process description (string)",
  "activationState":"the state of the process definition (ENABLED or DISABLED)",
  "name":"the process name (string)",
  "deployedBy":"the id of the user who deployed the process (integer)",
  "displayName":"the human readable process description (string)",
  "actorinitiatorid":"the id of the actor that can initiate cases of the process",
  "last_update_date":"the date when the process definition was last updated (date)",
  "configurationState":"the configuration state of the process (UNRESOLVED or RESOLVED)",
  "version":"the version of the process (string)"
 */