package com.example.recyclerviewmvvm.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerviewmvvm.model.EmployeeData
import com.example.recyclerviewmvvm.retrofit.ApiInterface
import retrofit2.Response

class EmployeeRepository(private val apiInterface: ApiInterface):ApiRequest()
{
    suspend fun getEmployeeData() = apiRequest {
        apiInterface.getEmployeedata(12)
    }
}