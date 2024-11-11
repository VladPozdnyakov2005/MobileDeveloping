
package com.example.mobiledeveloping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My First App") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Snackbar",
                                "",
                                withDismissAction = false,
                                SnackbarDuration.Long
                            )
                        }
                    }) {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Menu")
                    }
                }
            )
        },

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },

        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(onClick = { viewModel.onCalendarClick() }, Modifier.size(50.dp))
                    {
                        Icon(
                            Icons.Filled.DateRange, contentDescription = "Date",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = { viewModel.onHomeClick() }, Modifier.size(50.dp))
                    {
                        Icon(
                            Icons.Filled.Home, contentDescription = "Home",
                            modifier = Modifier.size(40.dp)
                        )

                    }
                    IconButton(onClick = { viewModel.onProfileClick() }, Modifier.size(50.dp))
                    {
                        Icon(
                            Icons.Filled.AccountCircle, contentDescription = "Account",
                            modifier = Modifier.size(40.dp)
                        )

                    }
                }

            }

        },

        ) { paddingValues ->


        SnackbarHost(
            modifier = Modifier,
            hostState = snackbarHostState
        ) {

        }

        when {
            viewModel.isProfileClicked.value -> Profile(paddingValues)
            viewModel.isHomeClicked.value -> ChatList(paddingValues, navController)
            viewModel.isCalendarClicked.value -> Calendar(paddingValues)
        }
    }
}

@Composable
fun ChatList(paddingValues: PaddingValues, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingValues)

    ) {
        Text(
            "Hello! Thats your last chats", modifier = Modifier
                .padding(15.dp),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold
        )
        LazyColumn() {
            items(100) { index ->
                Row(
                    modifier = Modifier
                        .bottomBorder(1.dp, Color.Black)
                        .clickable {
                            navController.navigate(Screens.ChatScreen.screenName + "/$index")
                        }


                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "desc",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(color = Color.Gray)
                    )
                    Column() {
                        Text(
                            "Chat number $index",
                            fontFamily = Monospace,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "That a simple example for chat number $index. I " +
                                    "just want to see this text on two lines so that you can try to make a restriction"
                        )
                    }
                }

            }
        }

    }
}

@Composable
fun Profile(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(text = "Hello, Dear User!")
        Row {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "avatar",
                modifier = Modifier
                    .padding(10.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray))
            Column {
                Text(text = "Vlad Pozdnyakov", modifier = Modifier.padding(10.dp))
                Text(text = "19 years old", modifier = Modifier.padding(7.dp))
            }

        }

    }
}


@Composable
fun Calendar(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(text = "Mobile Developing")

    }
}


@Preview
@Composable
private fun preview() {
    MainScreen(rememberNavController())
}


fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)
