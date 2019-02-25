package sa.com.etucook.retrofit

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

val recup = "data=[out:json];%20node%20[amenity=marketplace]%20({min_lat},{min_lon},{max_lat},{max_lon});%20out;"

interface RetrofitService {

    @GET("/interpreter")
    /*fun getMarkets(@Path("min_lat") minLat: Float,
                   @Path("min_lon") minLon: Float,
                   @Path("max_lat") maxLat: Float,
                   @Path("max_lon") maxLon: Float
    ): Call<Markets>*/
    fun getMarkets(@Query("data") data: String) : Call<Markets>


}