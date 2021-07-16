package com.ptideakolaborasiutama.list.ui.listMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.data.local.MovieEntity

class ListMovieViewModel(private var repository: Repository): ViewModel(){
    fun getListMovie(id:Int): LiveData<Resource<List<MovieEntity>>> = repository.getMovieFromGenre(id)
}