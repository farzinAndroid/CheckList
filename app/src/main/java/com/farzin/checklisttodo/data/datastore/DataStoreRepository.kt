package com.farzin.checklisttodo.data.datastore

interface DataStoreRepository {

    suspend fun putString(value:String,key:String)
    suspend fun getString(key: String):String?
    suspend fun putInt(value:Int,key:String)
    suspend fun getInt(key:String) : Int?

}