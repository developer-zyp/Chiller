package com.example.chiller.ui.barlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chiller.model.BarInfo
import com.example.chiller.network.ApiRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BarListViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    val newBarList = MutableLiveData<List<BarInfo>>()
    val popularBarList = MutableLiveData<List<BarInfo>>()
    val categoryBarList = MutableLiveData<List<BarInfo>>()
    val searchItemList = MutableLiveData<List<BarInfo>>()

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception : ${throwable.localizedMessage}"
        loading.value = false
    }

    private val context = Dispatchers.IO + exceptionHandler

    fun getNewBarList() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getBarList("1")
            popularBarList.postValue(value)
            loading.value = false
        }
    }

    fun getPopularBarList() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getBarList("2")
            newBarList.postValue(value)
            loading.value = false
        }
    }

    fun getCategoryBarList() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getBarList("3")
            categoryBarList.postValue(value)
            loading.value = false
        }
    }

    fun getSearchList(query: String) {
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getSearchBar(query)
            searchItemList.postValue(value)
        }
    }

}