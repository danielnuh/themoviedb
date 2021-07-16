package com.ptideakolaborasiutama.list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.BuildConfig
import com.ptideakolaborasiutama.list.data.local.*
import com.ptideakolaborasiutama.list.data.remote.RemoteDataSource
import com.ptideakolaborasiutama.list.data.remote.response.*

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
):DataSource{
    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(
                    remoteData,
                ).apply { instance = this }
            }
    }

    override fun getNowPlaying(): LiveData<Resource<List<MovieEntity>>> {
        val result = MutableLiveData<Resource<List<MovieEntity>>>()

        result.value = Resource.loading(null)

        remoteDataSource.getNowPlaying(object : RemoteDataSource.NowPlayingCallback{
            override fun onResult(movieResponse: List<ResultsItemNowPlaying>) {
                val temp =  ArrayList<MovieEntity>()

                for (movie in movieResponse){
                    temp.add(
                        MovieEntity(
                        movie.id,
                        movie.title,
                        BuildConfig.IMG_URL+movie.posterPath,
                            movie.voteAverage/2
                        )
                    )
                }

                result.value = Resource.success(temp)
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }
        })

        return result
    }

    override fun getTopRated(): LiveData<Resource<List<MovieEntity>>> {
        val result = MutableLiveData<Resource<List<MovieEntity>>>()

        result.value = Resource.loading(null)

        remoteDataSource.getTopRated(object : RemoteDataSource.TopRatedCallback {
            override fun onResult(movieResponse: List<ResultsItemTopRated>) {
                val temp =  ArrayList<MovieEntity>()

                for (movie in movieResponse){
                    temp.add(
                        MovieEntity(
                            movie.id,
                            movie.title,
                            BuildConfig.IMG_URL+movie.posterPath,
                            movie.voteAverage/2
                        )
                    )
                }

                result.value = Resource.success(temp)
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }

        })

        return result
    }

    override fun getDetail(id: Int): LiveData<Resource<MovieDetailEntity>> {
        val result = MutableLiveData<Resource<MovieDetailEntity>>()

        result.value = Resource.loading(null)

        remoteDataSource.getDetail(id, object : RemoteDataSource.DetailCallback {
            override fun onResult(movieResponse: DetailResponse) {
                val genreEntity = ArrayList<GenreEntity>()

                for (genre in movieResponse.genres){
                    genreEntity.add(GenreEntity(
                        genre.id,
                        genre.name
                    ))
                }

                result.value = Resource.success(
                    MovieDetailEntity(
                        movieResponse.id,
                        movieResponse.title,
                        BuildConfig.IMG_URL+movieResponse.posterPath,
                        movieResponse.overview,
                        movieResponse.voteAverage/2,
                        genreEntity
                    )
                )

            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }

        })

        return result
    }

    override fun getVideo(id: Int): LiveData<Resource<VideoEntity>> {
        val result = MutableLiveData<Resource<VideoEntity>>()

        result.value = Resource.loading(null)

        remoteDataSource.getVideo(id, object : RemoteDataSource.VideoCallback {
            override fun onResult(videoResultsItem: List<VideoResultsItem>) {

                if (!videoResultsItem.isEmpty()){
                    result.value = Resource.success(
                        VideoEntity(
                            videoResultsItem[0].key
                        )
                    )
                }else{
                    result.value = Resource.error("")
                }
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }
        })

        return result
    }

    override fun getCrew(id: Int): LiveData<Resource<CrewEntity>> {
        val result = MutableLiveData<Resource<CrewEntity>>()

        result.value = Resource.loading(null)

        remoteDataSource.getCredits(id, object : RemoteDataSource.CreditsCallback {
            override fun onResult(crew: List<CrewItem>) {
                for(temp in crew){
                    if(temp.department.equals("Directing")){
                        result.value = Resource.success(CrewEntity(temp.name))
                        break
                    }
                }
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }
        })

        return result
    }

    override fun getReview(id: Int): LiveData<Resource<List<ReviewEntity>>> {
        val result = MutableLiveData<Resource<List<ReviewEntity>>>()

        result.value = Resource.loading(null)


        remoteDataSource.getReviews(id, object : RemoteDataSource.ReviewsCallback{
            override fun onResult(review: List<ResultsItemReview>) {
                val reviews = ArrayList<ReviewEntity>()

                for (temp in review){
                    reviews.add(ReviewEntity(
                        temp.authorDetails.username,
                        temp.authorDetails.rating?.div(2),
                        temp.content,
                        temp.authorDetails.avatarPath
                    ))
                }

                result.value = Resource.success(reviews)
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }

        })


        return result
    }

    override fun getGenre(): LiveData<Resource<List<GenreEntity>>> {
        val result = MutableLiveData<Resource<List<GenreEntity>>>()

        result.value = Resource.loading(null)

        remoteDataSource.getGenre(object : RemoteDataSource.GenreCallback {
            override fun onResult(genre: List<GenresItem>) {
                val genres = ArrayList<GenreEntity>()

                for(item in genre){
                    genres.add(
                        GenreEntity(
                        item.id,
                        item.name
                    )
                    )
                }

                result.value = Resource.success(genres)
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }

        })

        return result
    }

    override fun getMovieFromGenre(id: Int): LiveData<Resource<List<MovieEntity>>> {
        val result = MutableLiveData<Resource<List<MovieEntity>>>()

        result.value = Resource.loading(null)

        remoteDataSource.getDiscover(id, object : RemoteDataSource.DiscoverCallback {
            override fun onResult(discover: List<DiscoverResultsItem>) {
                val discovers = ArrayList<MovieEntity>()

                for(item in discover){
                    discovers.add(
                        MovieEntity(
                        item.id,
                        item.title,
                        BuildConfig.IMG_URL+item.posterPath,
                        item.voteAverage/2
                    )
                    )
                }

                result.value = Resource.success(discovers)
            }

            override fun onFailure(string: String) {
                result.value = Resource.error(string)
            }

        })

        return result
    }
}