package tn.oussama.mp_android_bonita.framework.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import tn.oussama.mp_android_bonita.MyApplication
import tn.oussama.mp_android_bonita.framework.network.interceptors.AddCookiesInterceptor
import tn.oussama.mp_android_bonita.framework.network.interceptors.ReceivedCookiesInterceptor


fun getConfiguredHttpClientWithAuthenticationInterceptors() : OkHttpClient {
    val logging =  HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    val builder =  OkHttpClient.Builder()

    builder.addInterceptor(AddCookiesInterceptor())
    builder.addInterceptor( ReceivedCookiesInterceptor())

    builder.addInterceptor(logging);
    return builder.build()
}