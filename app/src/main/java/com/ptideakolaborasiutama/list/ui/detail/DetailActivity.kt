package com.ptideakolaborasiutama.list.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.e.list.viewModel.ViewModelFactory
import com.e.list.vo.Status
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.snackbar.Snackbar
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.databinding.ActivityDetailBinding
import com.ptideakolaborasiutama.list.ui.home.TopRatedAdapter


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(EXTRA_MOVIE,-1)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, vactory)[DetailViewModel::class.java]

        viewModel.getDetail(id).observe(this,{ result->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        val movieDetailEntity = result.data
                        if (movieDetailEntity != null) {
                            with(binding){
                                title.text = movieDetailEntity.title
                                storyline.text = movieDetailEntity.overview
                                stars.text = movieDetailEntity.stars.toString()

                                for (genreEntity in movieDetailEntity.genres){
                                    val chipTemp = Chip(this@DetailActivity)

                                    val drawable = ChipDrawable.createFromAttributes(this@DetailActivity, null, 0,  R.style.ChipCustom)
                                    chipTemp.setChipDrawable(drawable)
                                    chipTemp.setTextAppearanceResource(R.style.ChipCustom)

                                    chipTemp.setText(genreEntity.name)
                                    chip.addView(chipTemp)
                                }

                                Glide.with(this@DetailActivity)
                                    .load(movieDetailEntity.posterPath)
                                    .into(image)
                            }
                        }
                    }
                }
            }
        })

        viewModel.getVideo(id).observe(this, {result ->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        binding.btnWatch.visibility = View.GONE
                    }

                    Status.SUCCESS ->{
                        val videoEntity = result.data
                        if (videoEntity != null) {
                            with(binding){
                                btnWatch.visibility = View.VISIBLE
                                btnWatch.setOnClickListener {
                                    val appIntent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoEntity.key))
                                    val webIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://www.youtube.com/watch?v="+videoEntity.key)
                                    )
                                    try {
                                        startActivity(appIntent)
                                    } catch (ex: ActivityNotFoundException) {
                                        startActivity(webIntent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })

        viewModel.getDirector(id).observe(this, { result->
            if (result !=  null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        val crewEntity = result.data
                        if (crewEntity != null) {
                            with(binding){
                                director.text = "Director: "+crewEntity.director
                            }
                        }
                    }
                }
            }
        })

        val reviewAdapter = ReviewAdapter()

        viewModel.getReview(id).observe(this, { result->
            if (result != null){
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(window.decorView.rootView, "Failed load data", Snackbar.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS ->{
                        result.data?.let { reviewAdapter.setData(it) }
                        reviewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        with(binding.rvReview){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }


        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}