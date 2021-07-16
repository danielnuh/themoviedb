package com.ptideakolaborasiutama.list.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.list.vo.Resource
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.data.local.GenreEntity

class GenreViewModel(private var repository: Repository):ViewModel() {
    fun getGenre(): LiveData<Resource<List<GenreEntity>>> = repository.getGenre()
}