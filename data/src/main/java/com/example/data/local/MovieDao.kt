package com.example.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.MovieModel

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieModel>)

    @Query("select * from movie where genreIds like :genre order by popularity desc")
    fun getByGenre(genre: String): PagingSource<Int, MovieModel>

    @Query("select count(*) from movie where genreIds like :genre")
    suspend fun countByGenre(genre: String): Int

    @Query("delete from movie where genreIds like :genre")
    suspend fun deleteByGenre(genre: Int)
}