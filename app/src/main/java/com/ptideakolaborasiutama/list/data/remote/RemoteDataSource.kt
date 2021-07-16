package com.ptideakolaborasiutama.list.data.remote

import com.ptideakolaborasiutama.list.data.remote.api.ApiConfig
import com.ptideakolaborasiutama.list.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource().apply { instance = this }
        }
    }

    fun getNowPlaying(callback: NowPlayingCallback) {
        ApiConfig.getApiService().getNowPlaying(1).enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getTopRated(callback: TopRatedCallback){
       ApiConfig.getApiService().getTopRated(1).enqueue(object : Callback<TopRatedResponse> {
            override fun onResponse(call: Call<TopRatedResponse>, response: Response<TopRatedResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getDetail(id:Int, callback: DetailCallback){
        ApiConfig.getApiService().getDetail(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onResult(it) }
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getVideo(id: Int, callback: VideoCallback){
        ApiConfig.getApiService().getVideo(id).enqueue(object : Callback<VideoResponse>{
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getCredits(id:Int, callback:CreditsCallback){
        ApiConfig.getApiService().getCredit(id).enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(
                call: Call<CreditsResponse>,
                response: Response<CreditsResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.crew)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                callback.onFailure("")

            }

        })
    }

    fun getReviews(id:Int, callback:ReviewsCallback){
        ApiConfig.getApiService().getReview(id).enqueue(object : Callback<ReviewResponse>{
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getGenre(callback:GenreCallback){
        ApiConfig.getApiService().getGenre().enqueue(object : Callback<GenreResponse>{
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.genres)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    fun getDiscover(id:Int, callback:DiscoverCallback){
        ApiConfig.getApiService().getByGenre(id,1).enqueue(object : Callback<DiscoverResponse>{
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results)
                } else {
                    callback.onFailure("")
                }
            }

            override fun onFailure(call: Call<DiscoverResponse>, t: Throwable) {
                callback.onFailure("")
            }

        })
    }

    interface DiscoverCallback{
        fun onResult(discover: List<DiscoverResultsItem>)
        fun onFailure(string :String)
    }

    interface GenreCallback{
        fun onResult(genre: List<GenresItem>)
        fun onFailure(string :String)
    }

    interface ReviewsCallback{
        fun onResult(crew: List<ResultsItemReview>)
        fun onFailure(string :String)
    }

    interface CreditsCallback{
        fun onResult(crew: List<CrewItem>)
        fun onFailure(string :String)
    }

    interface DetailCallback{
        fun onResult(movieResponse: DetailResponse)
        fun onFailure(string :String)
    }

    interface VideoCallback{
        fun onResult(videoResultsItem: List<VideoResultsItem>)
        fun onFailure(string :String)
    }

    interface NowPlayingCallback{
        fun onResult(movieResponse: List<ResultsItemNowPlaying>)
        fun onFailure(string :String)
    }

    interface TopRatedCallback{
        fun onResult(movieResponse: List<ResultsItemTopRated>)
        fun onFailure(string :String)
    }

}