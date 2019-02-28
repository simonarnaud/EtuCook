package sa.com.etucook.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    const val BASE_URL = "http://overpass.openstreetmap.fr/api/interpreter/"

    private fun setRetrofitService(): RetrofitService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    fun getMarkets(/*minLat: Float, minLon: Float, maxLat: Float, maxLon: Float*/) /*: Markets*/ {
        /*setRetrofitService().getMarkets(minLat, minLon, maxLat, maxLon).enqueue(object : Callback<Markets> {
            override fun onResponse(call: Call<Markets>, response: Response<Markets>) {
                val markets = response.body()
                if (markets != null && markets.elements.isNotEmpty()) {
                    println("HERE is ALL MARKET FROM API:")
                    for (market in markets.elements)
                        println(" one market : ${market.tags.name}")
                }
            }
            override fun onFailure(call: Call<Markets>, t: Throwable) {
                error("Error occured when trying to get markets")
            }
        })*/

        /*setRetrofitService().getMarkets("[out:json];%20node%20[amenity=marketplace]%20" +
                "(49.151676,-0.423753,49.194477,-0.344102)" +
                ";%20out;")
            .enqueue(object : Callback<Markets> {
            override fun onResponse(call: Call<Markets>, response: Response<Markets>) {
                val markets = response.body()
                if (markets != null && markets.elements.isNotEmpty()) {
                    println("HERE is ALL MARKET FROM API:")
                    for (market in markets.elements)
                        println("One market : ${market.tags.name}")
                }
            }
            override fun onFailure(call: Call<Markets>, t: Throwable) {
                error("Error occured when trying to get markets")
            }
        })*/

        setRetrofitService().test("http://overpass.openstreetmap.fr/api/interpreter?data=[out:json];%20node%20[amenity=marketplace]%20(49.151676, -0.423753,49.194477, -0.344102);%20out;")
            .enqueue(object : Callback<Markets> {
                override fun onResponse(call: Call<Markets>, response: Response<Markets>) {
                    val markets = response.body()
                    if (markets != null && markets.elements.isNotEmpty()) {
                        println("HERE is ALL MARKET FROM API:")
                        for (market in markets.elements)
                            println("One market : ${market.tags.name}")
                    }
                }
                override fun onFailure(call: Call<Markets>, t: Throwable) {
                    error("Error occured when trying to get markets")
                }
            })
    }


}