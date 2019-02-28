package sa.com.etucook.retrofit

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    const val BASE_URL = "http://overpass.openstreetmap.fr/api/"

    private fun setRetrofitService(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
       return retrofit.create(RetrofitService::class.java)

    }

    private fun getMarkets(minLat : Float, minLon : Float, maxLat : Float, maxLon : Float) {

        val stringRequest = "http://overpass.openstreetmap.fr/api/interpreter" +
                "?data=[out:json];%20node%20[amenity=marketplace]" +
                "%20($minLat,$minLon,$maxLat,$maxLon)" +
                ";%20out;"

        setRetrofitService().getMarkets(stringRequest).enqueue(object : Callback<Markets> {
            override fun onResponse(call: Call<Markets>, response: Response<Markets>) {
                val markets = response.body()
                if (markets != null) {
                    for (m in markets.elements)
                        println(" one market place : ${m.lat} : ${m.lon} : ${m.tags.name}")
                }
            }
            override fun onFailure(call: Call<Markets>, t: Throwable) {
                println(call.request().url())
                error("Failed to retrieve data")
            }
        })
    }

    fun getMarketPlace(activity: Activity?) {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
        if(activeNetwork?.isConnected == true) {
            RetrofitFactory.getMarkets(49.151676F, -0.423753F, 49.194477F, -0.344102F)
        } else {
            Toast.makeText(activity, "You must have internet connection to do this", Toast.LENGTH_SHORT).show()
        }
    }
}