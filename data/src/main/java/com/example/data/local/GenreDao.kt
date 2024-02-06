package com.example.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.GenreModel

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genres: List<GenreModel>)

    @Query("select * from genre order by name asc")
    fun getAll(): PagingSource<Int, GenreModel>

    @Query("select count(*) from genre")
    suspend fun getCount(): Int

    @Query("delete from genre")
    suspend fun deleteAll()
}