package com.example.recyclerviewmvvm.retrofit

import com.example.recyclerviewmvvm.model.EmployeeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface
{
    @GET("users")
    suspend fun getEmployeedata(@Query("per_page")page:Int): Response<EmployeeData>
}