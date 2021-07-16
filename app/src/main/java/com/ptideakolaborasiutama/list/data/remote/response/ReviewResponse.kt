package com.ptideakolaborasiutama.list.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsItemReview>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class AuthorDetails(

	@field:SerializedName("avatar_path")
	val avatarPath: String? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Float? = null,

	@field:SerializedName("username")
	val username: String
)

data class ResultsItemReview(

	@field:SerializedName("author_details")
	val authorDetails: AuthorDetails,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("url")
	val url: String
)
