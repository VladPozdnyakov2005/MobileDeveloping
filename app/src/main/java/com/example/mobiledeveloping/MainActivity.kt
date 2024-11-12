package com.example.mobiledeveloping

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledeveloping.data.HourDto
import com.example.mobiledeveloping.data.WeatherModel
import com.example.mobiledeveloping.ui.theme.MobileDevelopingTheme
import com.example.mobiledeveloping.ui_components.DialogSearch
import com.example.mobiledeveloping.ui_components.MainScreen
import com.example.mobiledeveloping.ui_components.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val API_KEY = "3f38dcd2a0d6496a98b61205240911"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDevelopingTheme {
                val daysList = remember{
                    mutableStateOf(listOf<WeatherModel>())
                }
                val dialogState = remember{
                    mutableStateOf(false)
                }

                val currentDay = remember{
                    mutableStateOf(WeatherModel(
                        "",
                        "",
                        "0.0",
                        "",
                        "",
                        "0.0",
                        "0.0",
                        ""
                    )
                    )
                }
                if(dialogState.value){
                    DialogSearch(dialogState, onSubmit = {
                        getData(it, this, daysList, currentDay)
                    })
                }
                getData("London", this, daysList, currentDay)
                Image(
                    painter  = painterResource(
                        id = R.drawable.weather_bg
                    ),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds
                )
                Column{
                    MainScreen(currentDay, onClickSync = {
                        getData("London", this@MainActivity, daysList, currentDay)
                    }, onClickSearch = {
                        dialogState.value = true
                    }
                    )
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}

private fun getData(
    city: String, context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("\"http://api.weatherapi.com/v1/forecast.json?key=$API_KEY\"")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    val job = CoroutineScope(Dispatchers.IO).launch() {
        val weather = service.getWeather(city)

        daysList.value = weather.forecast.forecastday.map { forecastDay ->
            WeatherModel(
                city = weather.location.name,
                time = forecastDay.date,
                currentTemp = "",
                condition = forecastDay.day.condition.text,
                icon = forecastDay.day.condition.icon,
                maxTemp = forecastDay.day.maxTemp.toFloat().toInt().toString() + "°C",
                minTemp = forecastDay.day.minTemp.toFloat().toInt().toString() + "°C",
                hours = forecastDay.hour.toString()
            )
        }

        currentDay.value = daysList.value.first().copy(
            time = weather.current.time,
            currentTemp = weather.current.currentTemp.toFloat().toInt().toString() + "°C",
        )
    }
}


private fun getWeatherByHours(hours: List<HourDto>): List<WeatherModel> {
    if (hours.isEmpty()) return listOf()

    val list = ArrayList<WeatherModel>()

    hours.forEach { item ->

        list.add(
            WeatherModel(
                city = "",
                time = item.time,
                currentTemp = item.tempC.toFloat().toInt().toString() + "°C",
                condition = item.condition.text,
                icon = item.condition.icon,
                maxTemp = "",
                minTemp = "",
                hours = listOf()
            )
        )

    }
    return list
}






