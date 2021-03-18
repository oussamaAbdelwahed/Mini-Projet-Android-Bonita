package tn.oussama.mp_android_bonita.framework.network.interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;
import tn.oussama.mp_android_bonita.MainActivity;


public class ReceivedCookiesInterceptor implements Interceptor {
   /* private Context context;
    public ReceivedCookiesInterceptor(Context context) {
       this.context = context;
    }*/

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Log.i("INTERCEPTORS","RESPONSE INTERCEPTOR");
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies  = (HashSet<String>) MainActivity.preferences.getStringSet("PREF_COOKIES", new HashSet<String>());
            //HashSet<String> cookies = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("PREF_COOKIES", new HashSet<String>());
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            //SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
            SharedPreferences.Editor memes = MainActivity.preferences.edit();
            memes.putStringSet("PREF_COOKIES", cookies).apply();
            memes.commit();
        }
        return originalResponse;
    }
}
