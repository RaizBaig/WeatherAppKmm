package data.Models

import domain.commonModels.ModelObj
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("coord")
    var coord:Coord?=null,
    @SerialName("weather")
    var weather:List<Weather>?=null,
    @SerialName("main")
    var main:Main?=null,
    @SerialName("wind")
    var wind:Wind?=null,
    @SerialName("clouds")
    var clouds:Clouds?=null,
    @SerialName("sys")
    var sys:Sys?=null,
    @SerialName("base")
    var base : String?=null,
    @SerialName("visibility")
    var visibility:Long?=0,
    @SerialName("dt")
    var dt:Long?=0,
    @SerialName("timeZone")
    var timeZone : Long?=0,
    @SerialName("id")
    var id :Long?=0,
    @SerialName("name")
    var name :String?=null,
    @SerialName("cod")
    var cod:Long?=0
) {
    fun model():ModelObj{
        return ModelObj(
            description =weather!![0].description,
            temp = main!!.temp,
            pressure = main!!.pressure,
            speed =wind!!.speed,
            deg = wind!!.deg,
            cityName =name,
            dt =dt,
        )
    }
}

@Serializable
data class Coord(
    @SerialName("lon")
    var lon :Long? = 0,
    @SerialName("lat")
    var lat:Long? =0
)

@Serializable
data class Weather(
    @SerialName("id")
    var id:Long?=0,
    @SerialName("main")
    var main :String?=null,
    @SerialName("description")
    var description:String?=null,
    @SerialName("icon")
    var icon: String?=null
)

@Serializable
data class Main(
    @SerialName("temp")
    var temp	:Long?=0,
    @SerialName("feels_like")
    var feels_like	:	Long?=0 ,
    @SerialName("pressure")
    var pressure	:	Long?=0,
    @SerialName("humidity")
    var  humidity	:	Long?=0
)

@Serializable
data class Wind(
    @SerialName("speed")
    var speed :Long? = 0,
    @SerialName("deg")
    var deg:Long? =0
)

@Serializable
data class Clouds(
    @SerialName("all")
    var all :Long? = 0
)

@Serializable
data class Sys(
    @SerialName("all")
    var type :Long? = 0,
    @SerialName("id")
    var id:Long? =0,
    @SerialName("country")
    var country:String?=null,
    @SerialName("sunrise")
    var sunrise:Long? =0,
    @SerialName("sunset")
    var sunset:Long? =0,
)

