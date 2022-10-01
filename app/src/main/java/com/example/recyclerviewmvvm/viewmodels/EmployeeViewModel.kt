package com.example.recyclerviewmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewmvvm.model.Data
import com.example.recyclerviewmvvm.model.EmployeeData
import com.example.recyclerviewmvvm.repository.EmployeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository) : ViewModel()
{
    var employeeLiveData = MutableLiveData<List<Data>>()
    val employee: MutableLiveData<List<Data>>
        get() = employeeLiveData

   /* suspend fun getEmplList(){
        val list  = repository.getEmployeeData()
        employeeLiveData.postValue(list)
    }*/

    fun getData()
    {
        viewModelScope.launch(Dispatchers.IO){
            val list = repository.getEmployeeData()
            employeeLiveData.postValue(list.data)
        }
    }
}