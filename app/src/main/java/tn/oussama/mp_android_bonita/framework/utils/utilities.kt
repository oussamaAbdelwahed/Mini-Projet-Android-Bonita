package tn.oussama.mp_android_bonita.framework.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.ID
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.gson.Gson
import okio.ByteString.Companion.toByteString
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.MainActivity
import java.io.Serializable
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


fun storeToSharedPreferences(key: String, value: Serializable) {
    val prefsEditor: SharedPreferences.Editor =  MainActivity.preferences.edit();
    val gson : Gson =  Gson()
    val json: String = gson.toJson(value)
    prefsEditor.putString(key, json);

    prefsEditor.commit();
}

fun getUserFromSharedPreferences(key: String) : User {
    val gson : Gson =  Gson()
    val json : String? = MainActivity?.preferences.getString(key, "NONE");
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
          val dp = DatePicker(ctx)
          val i = 1
          dp.id= i
          return dp
      }
      InputType.OFFSETDATETIME -> {
          val dp = DatePicker(ctx)
          val i = 2
          dp.id= i
          //    final LocalDateTime localDate = LocalDateTime.parse(input, DATE_FORMAT);
          return dp
      }
      InputType.BOOLEAN -> {
         return Switch(ctx)
      }
    }
    return null;
}

fun getInputValue(v: View?) :Any {
    when (v) {
     is EditText -> {
       return v.text.toString()
     }
     is DatePicker -> {
         var dayOfMonth = v.dayOfMonth
         var dayOfMonthTMP = ""
         var month = v.month + 1
         var monthTMP = ""
         val year = v.year

         if (dayOfMonth < 10) {
             dayOfMonthTMP = "0${dayOfMonth.toString()}"
         } else {
             dayOfMonthTMP = dayOfMonth.toString()
         }
         if (month < 10) {
             monthTMP = "0${month.toString()}"
         } else {
             monthTMP = month.toString()
         }
      if(v.id==1) {
        val  DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss",Locale.FRANCE);

        val formattedLDSTR= "$dayOfMonthTMP/$monthTMP/$year 00:00:00"
        val  localDate : LocalDateTime = LocalDateTime.parse(formattedLDSTR, DATE_FORMAT);
        return localDate.toString()
      }else if(v.id ==2){
          //OFFSETDATETIME WORK HERE
          val offsetDateTime = OffsetDateTime.of(year, month, dayOfMonth, 0, 0, 0, 0, ZoneOffset.of("+01:00"))
          return offsetDateTime.toString()
      }
     }
     is Switch -> {
        return v.isChecked
     }
    }
  return ""
}

fun showToastMessage(ctx: Context,msg:String,longDuration:Boolean=true) {
   Toast.makeText(ctx,msg,if(longDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

