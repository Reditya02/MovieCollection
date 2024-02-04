package com.example.data.response

data class MovieVideoResponse(
	val id: Int,
	val results: List<MovieVideoResultsItem>
)

data class MovieVideoResultsItem(
	val site: String,
	val size: Int,
	val iso31661: String,
	val name: String,
	val official: Boolean,
	val id: String,
	val type: String,
	val publishedAt: String,
	val iso6391: String,
	val key: String
)

