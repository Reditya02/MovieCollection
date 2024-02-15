package com.example.data.response

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(

	@SerializedName("id")
	val id: Int,

	@SerializedName("page")
	val page: Int,

	@SerializedName("total_pages")
	val totalPages: Int,

	@SerializedName("results")
	val results: List<MovieReviewResultsItem>,

	@SerializedName("total_results")
	val totalResults: Int
)

data class AuthorDetails(

	@SerializedName("avatar_path")
	val avatarPath: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("rating")
	val rating: Double,

	@SerializedName("username")
	val username: String
)

data class MovieReviewResultsItem(

	@SerializedName("author_details")
	val authorDetails: AuthorDetails,

	@SerializedName("updated_at")
	val updatedAt: String,

	@SerializedName("author")
	val author: String,

	@SerializedName("created_at")
	val createdAt: String,

	@SerializedName("id")
	val id: String,

	@SerializedName("content")
	val content: String,

	@SerializedName("url")
	val url: String
)
