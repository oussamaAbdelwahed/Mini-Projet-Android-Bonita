package tn.oussama.core.domain

import com.google.gson.annotations.Expose


data class ProcessContract (
    @Expose
    var inputs : List<ProcessInput>,
    @Expose
    val constraints : List<InputConstraint>?
){}