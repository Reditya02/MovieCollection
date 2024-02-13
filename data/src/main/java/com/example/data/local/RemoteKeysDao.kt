package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("select * from remote_keys where id = :id and genreId = :genreId")
    suspend fun getRemoteKeysId(id: String, genreId: Int): RemoteKeys?

    @Query("delete from remote_keys where genreId = :genreId")
    suspend fun deleteRemoteKeysByGenreId(genreId: Int)
}