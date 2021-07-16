package com.ptideakolaborasiutama.list.data.remote.api

import com.ptideakolaborasiutama.list.BuildConfig
import com.ptideakolaborasiutama.list.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY
    ): Call<NowPlayingResponse>

    @GET("movie/{id}")
    fun getDetail(
        @Path("id") id: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<DetailResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("page") page: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<TopRatedResponse>

    @GET("genre/movie/list")
    fun getGenre(
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<GenreResponse>

    @GET("movie/{id}/reviews")
    fun getReview(
        @Path("id") id: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<ReviewResponse>

    @GET("movie/{id}/videos")
    fun getVideo(
        @Path("id") id: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<VideoResponse>

    @GET("movie/{id}/credits")
    fun getCredit(
        @Path("id") id: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
    ): Call<CreditsResponse>

    @GET("discover/movie")
    fun getByGenre(
        @Query("with_genres") genre: Int,
        @Query("page") page: Int,
        @Query("api_key") token: String = BuildConfig.API_KEY,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Call<DiscoverResponse>
}