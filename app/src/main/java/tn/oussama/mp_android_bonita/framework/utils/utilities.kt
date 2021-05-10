package tn.oussama.mp_android_bonita.framework.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import com.google.gson.Gson
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.MainActivity
import java.io.Serializable
import java.lang.reflect.Type


fun storeToSharedPreferences(key: String, value: Serializable) {
    val prefsEditor: SharedPreferences.Editor =  MainActivity.preferences.edit();
    val gson : Gson =  Gson()
    val json: String = gson.toJson(value)
    prefsEditor.putString(key, json);

    prefsEditor.commit();
}

fun getUserFromSharedPreferences(key: String) : User {
    val gson : Gson =  Gson()
    val json : String? = MainActivity.preferences.getString(key, "NONE");
    return  gson.fromJson<User>(json, User::class.java)
}


fun clearUserSession() {
    val prefsEditor: SharedPreferences.Editor =  MainActivity.preferences.edit();
    prefsEditor.clear().commit()
}

fun createUIWidgetFromType(type: InputType, ctx: Context): View? {
    when(type) {
      InputType.TEXT -> {
         return EditText(ctx)
      }
      InputType.LOCALDATE -> {
         return DatePicker(ctx)
      }
      InputType.OFFSETDATETIME -> {
         return DatePicker(ctx)
      }
    }
    return null;
}

