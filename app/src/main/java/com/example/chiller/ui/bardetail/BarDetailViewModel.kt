package com.example.chiller.ui.bardetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chiller.database.DBHelper
import com.example.chiller.model.BarInfo
import com.example.chiller.network.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BarDetailViewModel : ViewModel() {
    private val dbHelper = DBHelper()
    private val apiRepository = ApiRepository()

    fun getSingleItem(id: Int): LiveData<BarInfo> =
        dbHelper.getSingleBarInfo(id)


    fun insertData(item: BarInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.insertBarInfo(item)
        }

    }

    fun deleteData(item: BarInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.deleteBarInfo(item)
        }
    }

    fun getAllData(): LiveData<List<BarInfo>> =
        dbHelper.getAllBarInfo
}