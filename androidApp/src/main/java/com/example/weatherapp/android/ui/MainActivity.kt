package com.example.weatherapp.android.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.weatherapp.android.databinding.ActivityMainBinding
import com.example.weatherapp.android.util.AppConstants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import data.Models.WeatherDto
import domain.commonModels.ModelObj

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var db = Firebase.firestore
    lateinit var vm:MainActivityVM
    lateinit var appConstants:AppConstants
    private lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        vm = MainActivityVM()
        appConstants = AppConstants()
        setContentView(binding.root)
//        vm.AddData()
//        vm.postData(appConstants.addList)
        setObserver()
        setOnCLickListener()
    }

    private fun setOnCLickListener() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before>count && s.toString().isEmpty()) {
                    vm.getData()
                }else{
                    vm.getDataByQuery(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotBlank()){
                    vm.getDataByQuery(s.toString())
                }
            }

        })
    }

    fun setObserver(){
        vm.weatherList.observe(this, Observer {weatherList ->
            weatherAdapter = WeatherAdapter(weatherList)
//            weatherAdapter.updateData(weatherList)
            binding.weatherRv.adapter = weatherAdapter

        })
    }
}
