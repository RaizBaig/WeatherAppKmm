package com.example.weatherapp.android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.android.util.AppConstants
import com.example.weatherapp.android.util.AppConstants.Companion.toMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import data.Models.WeatherDto
import domain.commonModels.ModelObj
import org.json.JSONObject

class MainActivityVM : ViewModel() {
    var db = Firebase.firestore
    private val _weatherList = MutableLiveData<ArrayList<ModelObj>>()
    var weatherList: LiveData<ArrayList<ModelObj>> = _weatherList

    init {
//        AddData()
        getData()
    }

    // Create a new user with a first and last name
    val user: WeatherDto = WeatherDto()


    // Add a new document with a generated ID
    fun add() {
        var json = JSONObject(AppConstants.jsonString)
        var jsonMap = json.toMap()
        db.collection("weather")
            .add(jsonMap)
            .addOnSuccessListener { documentReference ->
                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Error", "Error adding document", e)
            }
    }

    fun getData() {
        db.collection("weather")
            .get()
            .addOnSuccessListener { result ->
                val list = ArrayList<WeatherDto>()
                for (document in result) {
                    val data = AppConstants.convertHashToModelObj(document.data)
                    list.add(data)
                    Log.d("GetFireStoreData", "${document.id} => ${document.data}")
                }

                convertDataToModelList(list)
            }
            .addOnFailureListener { exception ->
                Log.w("GetFireStoreData", "Error getting documents.", exception)
            }
    }

    private fun convertDataToModelList(list: java.util.ArrayList<WeatherDto>) {
        val convertedList = ArrayList<ModelObj>()
        for (convertList in list) {
            val item = convertList.model()
            convertedList.add(item)
        }
        _weatherList.postValue(convertedList)
    }

    fun getDataByQuery(cityName: String) {
        db.collection("weather").whereEqualTo("name", cityName)
            .get()
            .addOnSuccessListener { result ->
                val list = ArrayList<WeatherDto>()
                for (document in result) {
                    val data = AppConstants.convertHashToModelObj(document.data)
                    list.add(data)
                    Log.d("GetFireStoreData", "${document.id} => ${document.data}")
                }

                convertDataToModelList(list)
            }
            .addOnFailureListener {
                Log.w("GetFireStoreData", "Error getting documents.", it)
            }

    }

}