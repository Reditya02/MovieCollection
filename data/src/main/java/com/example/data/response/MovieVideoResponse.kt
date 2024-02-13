package com.example.data.response

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
	@SerializedName("id")
	val id: Int,

	@SerializedName("results")
	val results: List<MovieVideoResultsItem>
)

data class MovieVideoResultsItem(
	@SerializedName("site")
	val site: String,

	@SerializedName("size")
	val size: Int,

	@SerializedName("iso31661")
	val iso31661: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("official")
	val official: Boolean,

	@SerializedName("id")
	val id: String,

	@SerializedName("type")
	val type: String,

	@SerializedName("published_at")
	val publishedAt: String,

	@SerializedName("iso6391")
	val iso6391: String,

	@SerializedName("key")
	val key: String
)