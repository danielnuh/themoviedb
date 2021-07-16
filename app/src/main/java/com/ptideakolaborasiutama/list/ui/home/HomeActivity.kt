package com.ptideakolaborasiutama.list.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.list.viewModel.ViewModelFactory
import com.e.list.vo.Status
import com.google.android.material.snackbar.Snackbar
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.databinding.ActivityHomeBinding
import com.ptideakolaborasiutama.list.ui.genre.GenreActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vactory)[HomeViewModel::class.java]

        val nowPlayingAdapter = NowPlayingAdapter()
        val topRatedAdapter = TopRatedAdapter()

        viewModel.getNowPlaying().observe(this, {result ->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        result.data?.let { nowPlayingAdapter.setData(it) }
                        nowPlayingAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        viewModel.getTopRated().observe(this, {result ->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        result.data?.let { topRatedAdapter.setData(it) }
                        topRatedAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        binding.btnSeemore.setOnClickListener {
            startActivity(Intent(this@HomeActivity, GenreActivity::class.java))
        }


        with(binding.rvNowPlaying){
            layoutManager = LinearLayoutManager(context,    LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = nowPlayingAdapter
        }

        with(binding.rvTopRated){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = topRatedAdapter
        }
    }
}