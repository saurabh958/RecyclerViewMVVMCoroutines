package com.example.recyclerviewmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.recyclerviewmvvm.databinding.ItemEmployeeDetailBinding
import com.example.recyclerviewmvvm.model.Data
import com.example.recyclerviewmvvm.model.EmployeeData

class EmployeeDataAdapter:RecyclerView.Adapter<EmployeeDataAdapter.MyViewHolder>() {

    var employeeList = mutableListOf<Data>()

    fun setEmpData(employeeList: List<Data>){
        this.employeeList = employeeList.toMutableList()
        notifyDataSetChanged()
    }
    inner class MyViewHolder(val binding:ItemEmployeeDetailBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemEmployeeDetailBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = employeeList[position]
        holder.binding.apply {
            tvFullName.text =  "${currentItem.first_name} ${currentItem.last_name}"
            tvEmail.text = currentItem.email
            val imagelink = currentItem.avatar
            imgProfile.load(imagelink){
                crossfade(true)
                crossfade(1000)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun getItemCount() :Int {
        return employeeList.size
    }
}