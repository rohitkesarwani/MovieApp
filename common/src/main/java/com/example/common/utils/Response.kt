package com.example.common.utils

sealed class Response<T>(val data:T?=null,val msg:String?=null) {

    object Idle:Response<Nothing>()
    object Loading:Response<Nothing>()
    class Success<T>(data: T):Response<T>(data,"success")
    class Error(msg: String):Response<String>(msg=msg)

}