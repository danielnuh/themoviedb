package com.ptideakolaborasiutama.list.ui.genre

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.data.local.GenreEntity
import com.ptideakolaborasiutama.list.data.local.ReviewEntity
import com.ptideakolaborasiutama.list.databinding.ItemGenreBinding
import com.ptideakolaborasiutama.list.databinding.ItemReviewBinding
import com.ptideakolaborasiutama.list.ui.detail.ReviewAdapter
import com.ptideakolaborasiutama.list.ui.listMovie.ListMovieActivity

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private val listGenre = ArrayList<GenreEntity>()

    fun setData(listGenre:List<GenreEntity>){
        this.listGenre.clear()
        this.listGenre.addAll(listGenre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size

    class GenreViewHolder(private val binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genreEntity: GenreEntity){
            with(binding){
                title.text = genreEntity.name

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,ListMovieActivity::class.java )
                    intent.putExtra(ListMovieActivity.LIST_MOVIE, genreEntity)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}