package com.example.recyclerviewmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recyclerviewmvvm.adapters.EmployeeDataAdapter
import com.example.recyclerviewmvvm.databinding.ActivityMainBinding
import com.example.recyclerviewmvvm.repository.EmployeeRepository
import com.example.recyclerviewmvvm.retrofit.ApiInterface
import com.example.recyclerviewmvvm.retrofit.RetrofitClient
import com.example.recyclerviewmvvm.viewmodels.EmployeeViewModel
import com.example.recyclerviewmvvm.viewmodels.EmployeeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var recyclerView: RecyclerView
    val employeeDataAdapter = EmployeeDataAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val apiService = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        val repository = EmployeeRepository(apiService)
        recyclerView  = binding.recyclerEmployee
        employeeViewModel = ViewModelProvider(this,EmployeeViewModelFactory(repository))[EmployeeViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val decoratin = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoratin)
        recyclerView.adapter = employeeDataAdapter
        employeeViewModel.employee.observe(this, Observer {
            employeeDataAdapter.setEmpData(it)
        })
        employeeViewModel.getData()
    }
}