package com.example.mobiledeveloping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobiledeveloping.ui.theme.MobileDevelopingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDevelopingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileDevelopingTheme {
        Greeting("Android")
    }
}
@Composable
internal fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.screenName) {
        composable(Screens.MainScreen.screenName){
            MainScreen(navController = navController)
        }
        composable(
            Screens.ChatScreen.screenName + "/{index}",
            arguments = listOf(navArgument("index"){type = NavType.IntType})
        ){
                navBackStack -> ChatScreen(navController = navController, navBackStack.arguments?.getInt("index"))
        }

    }
}