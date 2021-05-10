package tn.oussama.core.domain
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProcessInput (

    @Expose
    var inputs : List<ProcessInput>,


    @Expose
    var type : String,


    @Expose
    val description : String,


    @Expose
    val name : String,


    @Expose
    val multiple : Boolean,

){}