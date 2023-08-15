package com.example.weatherapp.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.android.databinding.WeatherItemBinding
import domain.commonModels.ModelObj

class WeatherAdapter(
    var list: MutableList<ModelObj>
) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    inner class MyViewHolder(val itemBinding: WeatherItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: ModelObj) {

            itemBinding.weatherDto = item

        }
    }
    fun updateData(weatherDto: MutableList<ModelObj>) {
        this.list = weatherDto
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = WeatherItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}