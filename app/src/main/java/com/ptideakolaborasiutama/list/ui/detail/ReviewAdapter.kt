package com.ptideakolaborasiutama.list.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptideakolaborasiutama.list.R
import com.ptideakolaborasiutama.list.data.local.ReviewEntity
import com.ptideakolaborasiutama.list.databinding.ItemReviewBinding

class ReviewAdapter:RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private val listReview = ArrayList<ReviewEntity>()

    fun setData(listReview: List<ReviewEntity>){
        this.listReview.clear()
        this.listReview.addAll(listReview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listReview[position])

    }

    override fun getItemCount(): Int = listReview.size

    class ReviewViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewEntity: ReviewEntity){
            with(binding){
                name.text = reviewEntity.name
                description.text = reviewEntity.description
                Glide.with(itemView.context)
                    .load(reviewEntity.image)
                    .error(R.drawable.ic_user)
                    .placeholder(R.drawable.ic_user)
                    .into(image)

                if(reviewEntity.rate != null){
                    if (reviewEntity.rate < 1.0f){
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
                    }else if(reviewEntity.rate == 1.0f){
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
                    }else if(reviewEntity.rate > 1.0f && reviewEntity.rate < 2.0f){
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
                    }else if(reviewEntity.rate == 2.0f){
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
                    }else if(reviewEntity.rate > 2.0f && reviewEntity.rate < 3.0f){
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
                    }else if(reviewEntity.rate == 3.0f){
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
                    }else if(reviewEntity.rate > 3.0f && reviewEntity.rate < 4.0f){
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
                    }else if(reviewEntity.rate == 4.0f){
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
                    }else if(reviewEntity.rate > 4.0f && reviewEntity.rate < 5.0f){
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
                    }else if(reviewEntity.rate == 5.0f){
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


}