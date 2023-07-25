package com.contlo.app.data.model

import com.contlo.app.common.Constants.SOMETHING_WENT_WRONG

sealed class ApiResponse<out T : Any> {
    class Failure(val errorCode: Int, val msg: String = SOMETHING_WENT_WRONG) :
        ApiResponse<Nothing>()

    class Exception(val throwable: Throwable?) : ApiResponse<Nothing>()
    class Success<out T : Any>(val data: T) : ApiResponse<T>()
}