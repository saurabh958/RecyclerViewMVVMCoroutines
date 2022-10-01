package com.example.recyclerviewmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewmvvm.repository.EmployeeRepository

class EmployeeViewModelFactory(private val repository: EmployeeRepository):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeViewModel(repository) as T
    }

}