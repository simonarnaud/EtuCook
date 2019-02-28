package sa.com.etucook.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitService {

    @GET
    fun getMarkets(@Url url: String): Call<Markets>


    //ne marche pas
    @GET("/interpreter")
    fun getMarketsWithDataQuery(@Query("data") data: String) : Call<Markets>
}