package co.julian.eltiempo.data.remote

import co.julian.eltiempo.data.remote.model.NasaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("search")
    suspend fun loadData(@Query("q") q:String): Response<NasaResponse>


}
