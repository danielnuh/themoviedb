package com.e.list.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.di.Injection
import com.ptideakolaborasiutama.list.ui.detail.DetailViewModel
import com.ptideakolaborasiutama.list.ui.genre.GenreViewModel
import com.ptideakolaborasiutama.list.ui.home.HomeViewModel
import com.ptideakolaborasiutama.list.ui.listMovie.ListMovieViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(GenreViewModel::class.java) -> {
                return GenreViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ListMovieViewModel::class.java) -> {
                return ListMovieViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}