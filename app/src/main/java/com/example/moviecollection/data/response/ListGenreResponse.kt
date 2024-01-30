package com.example.moviecollection.data.response

import com.google.gson.annotations.SerializedName

data class ListGenreResponse(

	@field:SerializedName("genres")
	val genres: List<Genres>
)

data class Genres(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
