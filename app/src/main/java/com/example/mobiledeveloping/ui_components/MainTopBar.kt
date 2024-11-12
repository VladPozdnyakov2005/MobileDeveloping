package com.example.mobiledeveloping.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.IconButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier
import androidx.compose.material3.Scaffold
import com.example.appinfo.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar (
    title: String,
    scaffoldState: ScaffoldState,
    onFavClick: () -> Unit
){
    val coroutine = rememberCoroutineScope()
    TopAppBar(title = {
        Text(text = title)
    },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutine.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onFavClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Fav"
                )
            }
        }
    )
}