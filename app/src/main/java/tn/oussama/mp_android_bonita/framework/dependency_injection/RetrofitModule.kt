package tn.oussama.mp_android_bonita.framework.dependency_injection

import dagger.Module
import dagger.hilt.InstallIn
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.android.components.ActivityComponent

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.oussama.mp_android_bonita.MyApplication
import tn.oussama.mp_android_bonita.framework.network.BONITA_REST_API_BASE_URL
import tn.oussama.mp_android_bonita.framework.network.interceptors.AddCookiesInterceptor
import tn.oussama.mp_android_bonita.framework.network.interceptors.ReceivedCookiesInterceptor
import tn.oussama.mp_android_bonita.framework.network.retrofit.CategoryRetrofit
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserNetworkMapper
import tn.oussama.mp_android_bonita.framework.network.retrofit.UserRetrofit
import tn.oussama.mp_android_bonita.framework.utils.getConfiguredHttpClientWithAuthenticationInterceptors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit.Builder{
        return Retrofit.Builder().baseUrl(BONITA_REST_API_BASE_URL).client(getConfiguredHttpClientWithAuthenticationInterceptors()).addConverterFactory(GsonConverterFactory
          .create(gson))
    }

    //this specially will take interface definition and generates a dynamic class IMPL from interface defined contract
    @Singleton
    @Provides
    fun provideProcessService(retrofit: Retrofit.Builder) : ProcessRetrofit {
        return  retrofit.build().create(ProcessRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder) : UserRetrofit {
        return  retrofit.build().create(UserRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit.Builder) : CategoryRetrofit {
        return  retrofit.build().create(CategoryRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideUserNetworkMapper(): UserNetworkMapper {
        return UserNetworkMapper()
    }
}