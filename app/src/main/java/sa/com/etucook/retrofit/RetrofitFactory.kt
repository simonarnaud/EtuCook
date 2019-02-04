package sa.com.etucook.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    const val BASE_URL = "https://jsonplaceholder.typicode.com"     //url a changer

  /*  fun setRetrofitServcie(): RetrofitService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory()//a faire
            .build().create(RetrofitService::class.java)
    }*/
}