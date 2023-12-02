package com.saitawngpha.koinretrofit.utils

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
data class DataStatus<out T>(
    val status: Status,
    val data: T? = null,
    val msg: String? = null
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {

        fun <T> loading(): DataStatus<T> {
            return DataStatus(Status.LOADING)
        }

        fun <T> success(data: T?): DataStatus<T> {
            return DataStatus(Status.SUCCESS, data = data)
        }

        fun <T> error(error: String): DataStatus<T> {
            return DataStatus(Status.ERROR, msg = error)
        }
    }
}