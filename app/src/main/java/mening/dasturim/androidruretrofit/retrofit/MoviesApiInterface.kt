package mening.dasturim.androidruretrofit.retrofit

import io.reactivex.Single
import mening.dasturim.androidruretrofit.data.module.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApiInterface {

    //    @GET("movie/top_rated")
//    fun getTopRatedMovies(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Call<MoviesResponse>

//    @Headers("api_key:ee1f67b218538f137d2b6c2892761ff9")
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query ("api_key") api_key: String
    ): Single<MoviesResponse>
}