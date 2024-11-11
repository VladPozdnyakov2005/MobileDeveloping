package com.example.mobiledeveloping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun ChatScreen(navController: NavController, argument: Int?) {
    Column(
        modifier = Modifier
    ) {
        Text(text = "Clown")
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            Modifier
                .size(50.dp)
        )
        {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = "Back",
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Text(text = "Это ваш чат под номером $argument")
    }
}