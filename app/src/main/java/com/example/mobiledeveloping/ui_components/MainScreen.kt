package com.example.mobiledeveloping.ui_components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.appinfo.MainViewModel
import com.example.utils.DrawerEvents
import com.example.utils.ListItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen (
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit
){
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCaroutineScope()
    val mainList = mainViewModel.mainList
    val topBarTitle = remember {
        mutableStateOf("Грибы")
    }
    LaunchedEffect(Unit){
        mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(
                title = topBarTitle.value,
                scaffoldState
            ){
                topBarTitle.value = "Избранное"
                mainViewModel.getFavorites()
            }
        },
        drawerContent = {
            Drawer_menu() { event ->
                when (event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainViewModel.getAllItemsBycategory(event.title)

                    }
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }

            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(mainList.value) { item ->
                MainListItem(item = item){listItem ->
                    onClick(listItem)
                }
            }
        }
    }
}