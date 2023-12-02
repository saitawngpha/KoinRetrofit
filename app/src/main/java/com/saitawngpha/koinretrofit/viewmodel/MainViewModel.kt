package com.saitawngpha.koinretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saitawngpha.koinretrofit.repository.ApiRepository
import com.saitawngpha.koinretrofit.response.ResponsePhoto
import com.saitawngpha.koinretrofit.utils.DataStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
class MainViewModel(private val repository: ApiRepository): ViewModel() {

    private val _photoList = MutableLiveData<DataStatus<List<ResponsePhoto.Hit>>>()
    val photoList : LiveData<DataStatus<List<ResponsePhoto.Hit>>>
        get() = _photoList

    fun getPhoto(searchQuery: String) = viewModelScope.launch {
        repository.getPhoto(searchQuery).collect{
            _photoList.value = it
        }
    }
}