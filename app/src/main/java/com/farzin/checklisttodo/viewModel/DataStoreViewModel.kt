package com.farzin.checklisttodo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.data.datastore.DataStoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repo: DataStoreRepositoryImpl) : ViewModel() {



    companion object {
        const val PROFILE_KEY = "PROFILE_KEY"
        const val SORT_KEY = "SORT_KEY"
    }


    fun saveProfile(value:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.putInt(value, PROFILE_KEY)
        }
    }


    fun getProfile() : Int = runBlocking {
        repo.getInt(PROFILE_KEY) ?: R.drawable.man_1
    }


    fun saveSort(value:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.putInt(value,SORT_KEY)
        }
    }

    fun getSort() : Int = runBlocking {
        repo.getInt(SORT_KEY) ?: 3
    }



}