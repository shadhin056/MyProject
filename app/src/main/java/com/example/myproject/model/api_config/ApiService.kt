package com.example.examshadhin.view.model.api_config

import Api
import com.example.examshadhin.view.util.IpConfigure
import com.example.myproject.model.CustomerRegResponse
import com.example.myproject.model.RegistrationModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    var baseurl = IpConfigure.getIp()
    var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer " +"")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(Api::class.java);

    fun reqForRegistration(model: RegistrationModel): Single<CustomerRegResponse> {
        return api.reqForRegistrationResponse(
            model.gender,
            model.name,
            model.status,
            model.email
        )
    }

}