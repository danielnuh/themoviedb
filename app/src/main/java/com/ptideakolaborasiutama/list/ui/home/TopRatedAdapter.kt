package com.ptideakolaborasiutama.list.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptideakolaborasiutama.list.data.local.MovieEntity
import com.ptideakolaborasiutama.list.databinding.ItemTopRatedBinding
import com.ptideakolaborasiutama.list.ui.detail.DetailActivity

class TopRatedAdapter:RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {
    private val listMovie = ArrayList<MovieEntity>()

    fun setData(listMovie : List<MovieEntity>){
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder(
            ItemTopRatedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    class TopRatedViewHolder(private val binding: ItemTopRatedBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            with(binding){
                tvTitle.text = movieEntity.title
                Glide.with(itemView.context)
                    .load(movieEntity.posterPath)
                    .into(image)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movieEntity.id)
                    itemView.context.startActivity(intent)
                }


                if (movieEntity.rate < 1.0f){
                    starH.visibility = View.VISIBLE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.VISIBLE
                    starI3.visibility = View.VISIBLE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.GONE
                    starA2.visibility = View.GONE
                    starA3.visibility = View.GONE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate == 1.0f){
                    starH.visibility = View.GONE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.VISIBLE
                    starI3.visibility = View.VISIBLE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.GONE
                    starA3.visibility = View.GONE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate > 1.0f && movieEntity.rate < 2.0f){
                    starH.visibility = View.VISIBLE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.VISIBLE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.GONE
                    starA3.visibility = View.GONE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate == 2.0f){
                    starH.visibility = View.GONE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.VISIBLE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.GONE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate > 2.0f && movieEntity.rate < 3.0f){
                    starH.visibility = View.VISIBLE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.GONE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate == 3.0f){
                    starH.visibility = View.GONE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.VISIBLE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.VISIBLE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate > 3.0f && movieEntity.rate < 4.0f){
                    starH.visibility = View.VISIBLE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.GONE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.VISIBLE
                    starA4.visibility = View.GONE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate == 4.0f){
                    starH.visibility = View.GONE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.GONE
                    starI5.visibility = View.VISIBLE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.VISIBLE
                    starA4.visibility = View.VISIBLE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate > 4.0f && movieEntity.rate < 5.0f){
                    starH.visibility = View.VISIBLE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.GONE
                    starI5.visibility = View.GONE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.VISIBLE
                    starA4.visibility = View.VISIBLE
                    starA5.visibility = View.GONE
                }else if(movieEntity.rate == 5.0f){
                    starH.visibility = View.GONE
                    starI1.visibility = View.GONE
                    starI2.visibility = View.GONE
                    starI3.visibility = View.GONE
                    starI4.visibility = View.GONE
                    starI5.visibility = View.GONE

                    starA1.visibility = View.VISIBLE
                    starA2.visibility = View.VISIBLE
                    starA3.visibility = View.VISIBLE
                    starA4.visibility = View.VISIBLE
                    starA5.visibility = View.VISIBLE
                }
            }
        }
    }
}