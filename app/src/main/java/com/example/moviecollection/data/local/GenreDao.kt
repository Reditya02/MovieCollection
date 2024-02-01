package com.example.moviecollection.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecollection.data.response.Genres

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genres: List<Genres>)

    @Query("select * from genre order by name asc")
    fun getAll(): PagingSource<Int, Genres>

    @Query("delete from genre")
    suspend fun deleteAll()
}