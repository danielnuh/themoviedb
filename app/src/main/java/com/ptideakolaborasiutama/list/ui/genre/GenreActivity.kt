package com.ptideakolaborasiutama.list.ui.genre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.list.viewModel.ViewModelFactory
import com.e.list.vo.Status
import com.google.android.material.snackbar.Snackbar
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.databinding.ActivityGenreBinding
import com.ptideakolaborasiutama.list.databinding.ActivityHomeBinding
import com.ptideakolaborasiutama.list.ui.home.HomeViewModel

class GenreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vactory)[GenreViewModel::class.java]

        val genreAdapter = GenreAdapter()

        viewModel.getGenre().observe(this, {result->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        result.data?.let { genreAdapter.setData(it) }
                        genreAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        with(binding.rvGenre){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = genreAdapter
        }
    }
}