package com.example.chiller.database

import androidx.lifecycle.LiveData
import com.example.chiller.BaseApp
import com.example.chiller.model.BarInfo

class DBHelper {

    private val db: AppDatabase = AppDatabase.getInstance(BaseApp.getInstance())

    val getAllBarInfo: LiveData<List<BarInfo>> = db.barInfoDao().getAll()

    fun getSingleBarInfo(id: Int): LiveData<BarInfo> {
        return db.barInfoDao().getDataById(id)
    }

    suspend fun insertBarInfo(item: BarInfo) {
        db.barInfoDao().insertData(item)
    }

    suspend fun deleteBarInfo(item: BarInfo) {
        db.barInfoDao().deleteData(item)
    }


}