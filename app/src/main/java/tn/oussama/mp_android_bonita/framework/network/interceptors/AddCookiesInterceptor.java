package tn.oussama.mp_android_bonita.framework.network.interceptors;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tn.oussama.mp_android_bonita.MainActivity;

public class AddCookiesInterceptor  implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    public  static final String   LOGIN_URL_TO_EXCEPT= "loginservice";
    //private Context context;

    /*public AddCookiesInterceptor(Context context) {
        this.context = context;
    }*/

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Log.i("INTERCEPTORS","REQUEST INTERCEPTOR");
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet<String>) MainActivity.preferences.getStringSet(PREF_COOKIES, new HashSet<String>());

        //HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());
        Request original = chain.request();
        if(!original.url().toString().contains(LOGIN_URL_TO_EXCEPT)){
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
