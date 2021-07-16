package com.ptideakolaborasiutama.list.ui.listMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.list.viewModel.ViewModelFactory
import com.e.list.vo.Status
import com.google.android.material.snackbar.Snackbar
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.data.local.GenreEntity
import com.ptideakolaborasiutama.list.databinding.ActivityHomeBinding
import com.ptideakolaborasiutama.list.databinding.ActivityListMovieBinding
import com.ptideakolaborasiutama.list.ui.home.HomeViewModel

class ListMovieActivity : AppCompatActivity() {
    companion object {
        const val LIST_MOVIE = "list_movie"
    }

    private lateinit var binding: ActivityListMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genreEntity = intent.getParcelableExtra<GenreEntity>(LIST_MOVIE)

        val vactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vactory)[ListMovieViewModel::class.java]

        val listMovieAdapter = ListMovieAdapter()

        viewModel.getListMovie(genreEntity!!.id).observe(this, { result->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        result.data?.let { listMovieAdapter.setData(it) }
                        listMovieAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        binding.genre.text = genreEntity.name

        with(binding.rvListMovie){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
    }
}