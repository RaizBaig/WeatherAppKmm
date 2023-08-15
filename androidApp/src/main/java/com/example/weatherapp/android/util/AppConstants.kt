package com.example.weatherapp.android.util

import com.google.gson.Gson
import data.Models.WeatherDto
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class AppConstants {

    companion object {

        var jsonString = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": -122.08,\n" +
                "    \"lat\": 37.39\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 800,\n" +
                "      \"main\": \"Clear\",\n" +
                "      \"description\": \"clear sky\",\n" +
                "      \"icon\": \"01d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 290.93,\n" +
                "    \"feels_like\": 289.18,\n" +
                "    \"pressure\": 1013,\n" +
                "    \"humidity\": 62\n" +
                "  },\n" +
                "  \"visibility\": 10000,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 1.54,\n" +
                "    \"deg\": 350\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 1\n" +
                "  },\n" +
                "  \"dt\": 1631198609,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 1,\n" +
                "    \"id\": 5122,\n" +
                "    \"country\": \"US\",\n" +
                "    \"sunrise\": 1631170676,\n" +
                "    \"sunset\": 1631215931\n" +
                "  },\n" +
                "  \"timezone\": -25200,\n" +
                "  \"id\": 420006353,\n" +
                "  \"name\": \"San Francisco\",\n" +
                "  \"cod\": 200\n" +
                "}"

        @Throws(JSONException::class)
        fun JSONObject.toMap(): Map<String, Any> {
            val map = mutableMapOf<String, Any>()
            val keysItr: Iterator<String> = this.keys()
            while (keysItr.hasNext()) {
                val key = keysItr.next()
                var value: Any = this.get(key)
                when (value) {
                    is JSONArray -> value = value.toList()
                    is JSONObject -> value = value.toMap()
                }
                map[key] = value
            }
            return map
        }

        @Throws(JSONException::class)
        fun JSONArray.toList(): List<Any> {
            val list = mutableListOf<Any>()
            for (i in 0 until this.length()) {
                var value: Any = this[i]
                when (value) {
                    is JSONArray -> value = value.toList()
                    is JSONObject -> value = value.toMap()
                }
                list.add(value)
            }
            return list
        }
        fun convertHashToModelObj(hashMap: MutableMap<String, Any>):WeatherDto {
            val gson = Gson()
            val jsonElement = gson.toJsonTree(hashMap)
            val weatherDto: WeatherDto = gson.fromJson(jsonElement, WeatherDto::class.java)
            return weatherDto
        }
    }


}