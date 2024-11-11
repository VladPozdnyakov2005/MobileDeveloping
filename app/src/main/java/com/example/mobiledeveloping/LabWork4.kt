package com.example.mobiledeveloping

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My First App") },
                navigationIcon = {
                    IconButton(onClick = {}){
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Menu" )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    IconButton(onClick = {})
                    {
                        Icon(Icons.Filled.DateRange, contentDescription = "Date",
                            modifier = Modifier.size(40.dp))
                    }
                    IconButton(onClick = {})
                    {
                        Icon(Icons.Filled.Home, contentDescription = "Home",
                            modifier = Modifier.size(40.dp))

                    }
                    IconButton(onClick = {})
                    {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Account",
                            modifier = Modifier.size(40.dp))

                    }
                }

            }
        },

        ) {padding ->
        padding
        Column(

            modifier = Modifier.fillMaxHeight()
                .padding(padding)

        ){
            Text("Hello! Thats your last chats", modifier = Modifier
                .padding(15.dp),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(){
                items(100){index->
                    Row (modifier = Modifier.bottomBorder(1.dp, Color.Black)

                    ){
                        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "desc", modifier = Modifier
                            .padding(10.dp)
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(color = Color.Gray)
                        )
                        Column (){
                            Text("Chat number $index", fontFamily = Monospace, fontWeight = FontWeight.Bold)
                            Text("That a simple example for chat number $index. I " +
                                    "just want to see this text on two lines so that you can try to make a restriction")
                        }
                    }

                }
            }

        }
    }
}

@Preview
@Composable
private fun preview() {
    SecondScreen()
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)