package com.example.mobiledeveloping.appinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.appinfo.ui.theme.AppInfoTheme
import com.example.ui_components.MainScreen
import com.example.utils.ListItem
import com.example.utils.Routes


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null
            AppInfoTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN
                ){
                    composable(Routes.MAIN_SCREEN){
                        MainScreen{listItem ->
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }

                    composable(Routes.INFO_SCREEN){

                    }
                }
            }
        }
    }
}