package com.example.mobiledeveloping.ui_components

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appinfo.ui.theme.BgTrans
import com.example.appinfo.ui.theme.MainRed
import com.example.mobiledeveloping.R
import com.example.utils.DrawerEvents
import java.lang.reflect.Modifier

@Composable

fun DrawerMenu (onEvent:(DrawerEvents) -> Unit){
    Box(modifier = Modifier.fillmaxSize()){
        Image(painter = painterResource(
            id = R.drawable.drawer_list_bg),
            contentDescription = "Main Bg image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()){
            Header()
            Body(){event ->
                onEvent(event)

            }

        }

    }
}

@Composable
fun Header(){
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(170.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, MainRed)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Layout.Alignment.BottomCenter
        ){
            Image(painter = painterResource(id = R.drawable.mush_bg),
                contentDescription = "Header",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "-СПРАВОЧНИК БОТАНИКА-",
                modifier = Modifier.fillMaxWidth()
                    .background(MainRed)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Body(onEvent:(DrawerEvents) -> Unit){
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn (modifier = Modifier.fillMaxSize()){
        itemsIndexed(list){index, title ->
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(3.dp),
                backgroundColor = BgTrans
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }
}