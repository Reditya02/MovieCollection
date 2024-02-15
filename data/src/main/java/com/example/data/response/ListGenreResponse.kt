package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ListGenreResponse(

	@SerializedName("genres")
	val genres: List<Genres>
)

data class Genres(

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: Int
)
