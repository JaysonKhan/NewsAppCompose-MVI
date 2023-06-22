package uz.gita.newsappcompose.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newsappcompose.data.remote.api.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val API_KEY =  "pub_2486830c89700f36e74d5e853835ca876c10e"

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context):OkHttpClient =
        OkHttpClient
        .Builder()
        .addInterceptor(
            ChuckerInterceptor
                .Builder(context)
                .build()
        ).addInterceptor { chain ->
                val request =    chain.request()
                val newUrl = request.url.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()
                val newRequest = request.newBuilder().url(newUrl).build()
                return@addInterceptor chain.proceed(newRequest)
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit2(okHttpClient: OkHttpClient):Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://newsdata.io")
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):NewsApi = retrofit.create(NewsApi::class.java)
}