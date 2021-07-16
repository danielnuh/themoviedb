package com.ptideakolaborasiutama.list.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.data.local.CrewEntity
import com.ptideakolaborasiutama.list.data.local.MovieDetailEntity
import com.ptideakolaborasiutama.list.data.local.ReviewEntity
import com.ptideakolaborasiutama.list.data.local.VideoEntity

class DetailViewModel(private var repository: Repository):ViewModel() {
    fun getDetail(id:Int): LiveData<Resource<MovieDetailEntity>> = repository.getDetail(id)

    fun getVideo(id:Int): LiveData<Resource<VideoEntity>> = repository.getVideo(id)

    fun getDirector(id: Int):LiveData<Resource<CrewEntity>> = repository.getCrew(id)

    fun getReview(id:Int):LiveData<Resource<List<ReviewEntity>>> = repository.getReview(id)
}