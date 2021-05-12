package tn.oussama.mp_android_bonita.framework.network.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tn.oussama.mp_android_bonita.MainActivity;


public class ReceivedCookiesInterceptor implements Interceptor {
   /* private Context context;
    public ReceivedCookiesInterceptor(Context context) {
       this.context = context;
    }*/

    public  static final String   LOGIN_URL_TO_EXCEPT= "loginservice";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Log.i("INTERCEPTORS","RESPONSE INTERCEPTOR");
        Response originalResponse = chain.proceed(chain.request());
        SharedPreferences.Editor memes = MainActivity.preferences.edit();

        Request original = chain.request();
        if(original.url().toString().contains(LOGIN_URL_TO_EXCEPT)) {
            //this is the wrapper of setting or not the cookies
          if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies  = (HashSet<String>) MainActivity.preferences.getStringSet("PREF_COOKIES", new HashSet<String>());
            for (String header : originalResponse.headers("Set-Cookie")) {
                if(header.contains("X-Bonita-API-Token")) {
                  //memes.putString("X-Bonita-API-Token", header.split("=",2)[1]).apply();
                  memes.putString("X-Bonita-API-Token",header.split("=",2)[1].split(";",2)[0]);
                }
                cookies.add(header);
            }
            memes.putStringSet("PREF_COOKIES", cookies).apply();
            memes.commit();
          }
        }
        return originalResponse;
    }
}
