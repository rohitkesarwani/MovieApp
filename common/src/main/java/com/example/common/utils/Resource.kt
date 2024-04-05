package com.example.common.utils

sealed class Resource<T>(val data:T?,val message:String?){
    class Idle<T>: Resource<T>(null,null)
    class Loading<T>: Resource<T>(null,null)
    class Success<T>(data: T?,message: String?): Resource<T>(data,message)
    class Error<T>(data: T?=null,message: String?): Resource<T>(data,message)
}