package com.example.data.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ListMovieByGenreResponse(

	@SerializedName("page")
	val page: Int,

	@SerializedName("total_pages")
	val totalPages: Int,

	@SerializedName("results")
	val listResults: List<MovieResultsItem>,

	@SerializedName("total_results")
	val totalResults: Int
)

data class MovieResultsItem(

	@SerializedName("overview")
	val overview: String? = null,

	@SerializedName("original_language")
	val originalLanguage: String? = null,

	@SerializedName("original_title")
	val originalTitle: String? = null,

	@SerializedName("video")
	val video: Boolean? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("genre_ids")
	val genreIds: List<Int>? = null,

	@SerializedName("poster_path")
	val posterPath: String? = null,

	@SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@SerializedName("release_date")
	val releaseDate: String? = null,

	@SerializedName("popularity")
	val popularity: Double? = 0.0,

	@SerializedName("vote_average")
	val voteAverage: Double? = null,

	@PrimaryKey
	@SerializedName("id")
	val id: Int,

	@SerializedName("adult")
	val adult: Boolean? = null,

	@SerializedName("vote_count")
	val voteCount: Int? = null
)
