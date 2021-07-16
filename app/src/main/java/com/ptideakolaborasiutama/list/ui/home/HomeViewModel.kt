package com.ptideakolaborasiutama.list.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.data.local.MovieEntity

class HomeViewModel(private var repository: Repository):ViewModel() {
    fun getNowPlaying():LiveData<Resource<List<MovieEntity>>> = repository.getNowPlaying()

    fun getTopRated(): LiveData<Resource<List<MovieEntity>>> = repository.getTopRated()
}