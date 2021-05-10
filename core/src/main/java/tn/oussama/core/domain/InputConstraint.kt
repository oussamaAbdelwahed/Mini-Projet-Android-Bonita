package tn.oussama.core.domain

import com.google.gson.annotations.Expose

data class InputConstraint(
@Expose
var name : String,
@Expose

var expression : String,
@Expose

var explanation : String,
@Expose

var inputNames : List<String>

) {
}