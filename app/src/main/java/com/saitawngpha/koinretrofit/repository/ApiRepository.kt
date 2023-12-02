package com.saitawngpha.koinretrofit.repository

import com.saitawngpha.koinretrofit.api.ApiServices
import com.saitawngpha.koinretrofit.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
class ApiRepository(private val apiServices: ApiServices) {

    suspend fun getPhoto(searchQuery: String) = flow{
        emit(DataStatus.loading())

        val result = apiServices.getPhoto(searchQuery)
        when(result.code()){
            200 -> emit(DataStatus.success(result.body()?.hits))
            400 -> emit(DataStatus.error(result.message()))
            500 -> emit(DataStatus.error(result.message()))
        }
    }
        .catch { emit(DataStatus.error(it.message.toString()))}
        .flowOn(Dispatchers.IO)

}