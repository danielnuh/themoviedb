package com.ptideakolaborasiutama.list.data

import androidx.lifecycle.LiveData
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.data.local.*

interface DataSource {
    fun getNowPlaying():LiveData<Resource<List<MovieEntity>>>

    fun getTopRated():LiveData<Resource<List<MovieEntity>>>

    fun getDetail(id:Int):LiveData<Resource<MovieDetailEntity>>

    fun getVideo(id: Int): LiveData<Resource<VideoEntity>>

    fun getCrew(id: Int): LiveData<Resource<CrewEntity>>

    fun getReview(id:Int):LiveData<Resource<List<ReviewEntity>>>

    fun getGenre():LiveData<Resource<List<GenreEntity>>>

    fun getMovieFromGenre(id:Int): LiveData<Resource<List<MovieEntity>>>
}