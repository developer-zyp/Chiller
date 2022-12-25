package com.example.chiller.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chiller.model.BarInfo

@Dao
interface BarInfoDao {

    @Query("SELECT * FROM BarInfo")
    fun getAll(): LiveData<List<BarInfo>>

    @Query("SELECT * FROM BarInfo WHERE resid = :Id")
    fun getDataById(Id: Int): LiveData<BarInfo>

    @Insert
    suspend fun insertData(item: BarInfo)

    @Insert
    suspend fun insertList(itemList: List<BarInfo>)

    @Update
    suspend fun updateData(item: BarInfo)

    @Delete
    suspend fun deleteData(item: BarInfo)

    @Query("DELETE FROM BarInfo")
    suspend fun deleteAll()
}