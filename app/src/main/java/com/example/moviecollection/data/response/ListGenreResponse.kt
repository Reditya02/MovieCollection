package com.example.moviecollection.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ListGenreResponse(

	@field:SerializedName("genres")
	val genres: List<Genres>
)

@Entity(tableName = "genre")
data class Genres(

	@field:SerializedName("name")
	val name: String,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int
)
