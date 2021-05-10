package tn.oussama.mp_android_bonita.framework.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
/*
*  val displayName:String,
      val name:String,
      val creationDate: String,
      val description:String?=""*/

class CategoryNetworkEntity(
    @SerializedName("id")
    @Expose
    val id: Long,


    @SerializedName("displayName")
    @Expose
    var displayName: String,

    @SerializedName("name")
    @Expose
    var name: String="",

    @SerializedName("creationDate")
    @Expose
    var creationDate: String?,

    @SerializedName("description")
    @Expose
    var description: String?,

){
}
