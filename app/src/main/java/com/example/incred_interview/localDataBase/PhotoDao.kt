package com.example.incred_interview.localDataBase



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photoentity")
    fun getAll(): Observable<List<PhotoEntity>>

    @Insert
    fun insertPhoto( todo: PhotoEntity):Completable

    @Query("DELETE FROM photoentity")
    fun deleteAll(): Completable
}